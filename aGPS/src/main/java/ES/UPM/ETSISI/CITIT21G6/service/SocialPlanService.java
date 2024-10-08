package ES.UPM.ETSISI.CITIT21G6.service;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.*;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException.FutureSocialPlanException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException.PastSocialPlanException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException.SocialPlanCollisionException;
import ES.UPM.ETSISI.CITIT21G6.exception.TicketException.InvalidScoreException;
import ES.UPM.ETSISI.CITIT21G6.model.*;
import ES.UPM.ETSISI.CITIT21G6.repository.SocialPlanRepository;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class SocialPlanService
{
    private SocialPlanRepository repository;
    private final Clock clock;

    public SocialPlanService(SocialPlanRepository repository, Clock clock)
    {
        this.repository = repository;
        this.clock = clock;
    }

    public SocialPlan createSocialPlan(String ownerName, String socialPlanName, LocalDateTime date, String location, OptionalInt capacity)
            throws PastDateException, InvalidCapacityException, SocialPlanAlreadyAddedException
    {
        if (date.isBefore(LocalDateTime.now(clock)))
            throw new PastDateException(date);

        SocialPlan socialPlan = new SocialPlan(ownerName, socialPlanName, date, capacity, location, clock);
        repository.save(socialPlan);
        return socialPlan;
    }

    public void deleteSocialPlan(SocialPlanId id) throws SocialPlanNotFoundException
    {
        repository.delete(id);
    }

    public Activity addActivity(SocialPlanId id, String activityName, String description, int duration, double price, ActivityType type, OptionalInt capacity)
            throws SocialPlanNotFoundException, InvalidCapacityException, ActivityAlreadyInSocialPlanException, PastSocialPlanException, InvalidPriceException, InvalidDurationException
    {
        SocialPlan socialPlan = repository.fetch(id);
        if (isPastSocialPlan(socialPlan))
            throw new PastSocialPlanException(socialPlan);

        Activity activity = new Activity(activityName, description, duration, capacity, price, type);

        socialPlan.addActivity(activity);
        repository.update(socialPlan);

        return activity;
    }

    public double checkPlanCost(SocialPlanId id, int age) throws SocialPlanNotFoundException
    {
        return repository.fetch(id)
                .getActivities()
                .stream()
                .reduce(0.0, (subtotal, activity) ->
                        subtotal + ActivityPriceCalculator.calculate(activity, age), Double::sum
                );
    }

    public void joinSocialPlan(SocialPlanId id, String participantName)
            throws SocialPlanNotFoundException, PastSocialPlanException, SocialPlanCollisionException, ParticipantAlreadyInSocialPlanException, FullSocialPlanException
    {
        SocialPlan socialPlan = repository.fetch(id);
        if (isPastSocialPlan(socialPlan))
            throw new PastSocialPlanException(socialPlan);

        Optional<SocialPlan> socialPlanCollision = repository.fetchAllSocialPlans()
                .stream()
                .filter(plan -> plan.getParticipants()
                        .stream()
                        .anyMatch(ticket -> participantName.equals(ticket.getUserName())))
                .filter(plan -> socialPlanCollision(socialPlan, plan))
                .findFirst();

        if (socialPlanCollision.isPresent())
            throw new SocialPlanCollisionException(socialPlanCollision.get());

        socialPlan.addParticipant(participantName);
        repository.update(socialPlan);
    }

    public void unjoinSocialPlan(SocialPlanId id, String participantName)
            throws SocialPlanNotFoundException, ParticipantNotFoundException, PastSocialPlanException
    {
        SocialPlan socialPlan = repository.fetch(id);
        if (isPastSocialPlan(socialPlan))
            throw new PastSocialPlanException(socialPlan);
        socialPlan.removeParticipant(participantName);
        repository.update(socialPlan);
    }

    public List<SocialPlan> listSocialPlans(ListOrder order)
    {
        Stream<SocialPlan> socialPlans = repository.fetchAllSocialPlans()
                .stream()
                .filter(plan -> !isPastSocialPlan(plan));

        socialPlans = switch (order)
        {
            case DATE -> socialPlans.sorted(Comparator.comparing(SocialPlan::getDate));
            case SCORE -> socialPlans.sorted(Comparator.comparing(plan -> -ownerScore(plan.getOwnerName())));
        };

        return socialPlans.toList();
    }

    public List<SocialPlan> listSubscribedSocialPlans(String participantName)
    {
        return repository
                .fetchAllSocialPlans()
                .stream()
                .filter(plan -> plan
                        .getParticipants()
                        .stream()
                        .anyMatch(ticket -> participantName.equals(ticket.getUserName())))
                .toList();
    }

    public void scoreSocialPlan(SocialPlanId id, String participantName, OptionalInt score)
            throws SocialPlanNotFoundException, ParticipantNotFoundException, InvalidScoreException, FutureSocialPlanException
    {
        SocialPlan socialPlan = repository.fetch(id);
        if (!isPastSocialPlan(socialPlan))
            throw new FutureSocialPlanException(socialPlan);

        Ticket ticket = socialPlan
                .getParticipants()
                .stream()
                .filter(t -> participantName.equals(t.getUserName()))
                .findAny()
                .orElseThrow(() -> new ParticipantNotFoundException(participantName));

        ticket.setScore(score);
        repository.update(socialPlan);
    }

    public List<Activity> getSocialPlanActivities(SocialPlanId id) throws SocialPlanNotFoundException
    {
        return repository.fetch(id).getActivities();
    }

    public List<Ticket> getParticipants(SocialPlanId id) throws SocialPlanNotFoundException
    {
        return repository.fetch(id).getParticipants();
    }

    public int getDuration(SocialPlanId id) throws SocialPlanNotFoundException
    {
        return calculateSocialPlanDuration(repository.fetch(id));
    }

    public void setSocialPlanCapacity(SocialPlanId id, OptionalInt capacity)
            throws SocialPlanNotFoundException, InvalidCapacityException, PastSocialPlanException
    {

        SocialPlan socialPlan = repository.fetch(id);
        if (isPastSocialPlan(socialPlan))
            throw new PastSocialPlanException(socialPlan);

        socialPlan.setCapacity(capacity);
        repository.update(socialPlan);
    }

    private boolean isPastSocialPlan(SocialPlan socialPlan)
    {
        return socialPlan.getDate().isBefore(LocalDateTime.now(clock));
    }

    private double ownerScore(String ownerName)
    {
        return repository
                .fetchAllSocialPlans()
                .stream()
                .filter(plan -> ownerName.equals(plan.getOwnerName()))
                .filter(this::isPastSocialPlan)
                .mapToDouble(SocialPlanService::averageScore)
                .average()
                .orElse((Ticket.MAXIMUM_SCORE + Ticket.MINIMUM_SCORE) / 2.0);
    }

    private static double averageScore(SocialPlan socialPlan)
    {
        return socialPlan
                .getParticipants()
                .stream()
                .filter(ticket -> ticket.getScore().isPresent())
                .mapToInt(ticket -> ticket.getScore().getAsInt())
                .average()
                .orElse((Ticket.MAXIMUM_SCORE + Ticket.MINIMUM_SCORE) / 2.0);
    }

    private static int calculateSocialPlanDuration(SocialPlan socialPlan)
    {
        int duration = 0;
        if (!socialPlan.getActivities().isEmpty())
        {
            int durationWithoutTrip = socialPlan.getActivities()
                    .stream()
                    .reduce(0, (x, activity) -> x + activity.getDuration(), Integer::sum);

            duration = durationWithoutTrip + 20 * socialPlan.getActivities().size() - 1;
        }

        return duration;
    }

    private static boolean socialPlanCollision(SocialPlan socialPlan1, SocialPlan socialPlan2)
    {
        LocalDateTime socialPlan1StartDate = socialPlan1.getDate();
        LocalDateTime socialPlan1EndDate = socialPlan1.getDate().plusMinutes(calculateSocialPlanDuration(socialPlan1));

        LocalDateTime socialPlan2StartDate = socialPlan2.getDate();
        LocalDateTime socialPlan2EndDate = socialPlan2.getDate().plusMinutes(calculateSocialPlanDuration(socialPlan2));

        return socialPlan1StartDate.isBefore(socialPlan2EndDate)
                && socialPlan1EndDate.isAfter(socialPlan2StartDate);
    }
}
