package ES.UPM.ETSISI.CITIT21G6;

import ES.UPM.ETSISI.CITIT21G6.CLI.CommandHandler;
import ES.UPM.ETSISI.CITIT21G6.CLI.ExitCommand;
import ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand.*;
import ES.UPM.ETSISI.CITIT21G6.CLI.userCommand.LoginUserCommand;
import ES.UPM.ETSISI.CITIT21G6.CLI.userCommand.LogoutUserCommand;
import ES.UPM.ETSISI.CITIT21G6.CLI.userCommand.RegisterUserCommand;
import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;
import ES.UPM.ETSISI.CITIT21G6.controller.UserController;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.*;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException.FutureSocialPlanException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException.PastSocialPlanException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException.SocialPlanCollisionException;
import ES.UPM.ETSISI.CITIT21G6.exception.TicketException.InvalidScoreException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAgeException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPhoneNumberException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.PhoneNumberAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.model.ActivityType;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;
import ES.UPM.ETSISI.CITIT21G6.repository.InMemorySocialPlanRepository;
import ES.UPM.ETSISI.CITIT21G6.repository.InMemoryUserRepository;
import ES.UPM.ETSISI.CITIT21G6.repository.SocialPlanRepository;
import ES.UPM.ETSISI.CITIT21G6.repository.UserRepository;
import ES.UPM.ETSISI.CITIT21G6.service.SocialPlanService;
import ES.UPM.ETSISI.CITIT21G6.service.UserService;
import ES.UPM.ETSISI.CITIT21G6.view.CLISocialPlanView;
import ES.UPM.ETSISI.CITIT21G6.view.CLIUserView;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class Main
{

    private static final String commandSeparator = " ";
    public static void main(String[] argv)
    {
        UserRepository userRepository = new InMemoryUserRepository();
        SocialPlanRepository socialPlanRepository = new InMemorySocialPlanRepository();

        if (argv.length < 1 || !argv[0].equals("no-mock"))
            mockData(userRepository, socialPlanRepository);

        UserController userController = new UserController(new UserService(userRepository), new CLIUserView());
        SocialPlanController socialPlanController = new SocialPlanController(new SocialPlanService(socialPlanRepository, Clock.systemDefaultZone()), new CLISocialPlanView());

        CommandHandler cli = systemCommandHandler(userController, socialPlanController);

        Scanner scanner = new Scanner(System.in);
        boolean finish = false;
        System.out.println("Social Plan Management Application - v1.0\nDevelop by group 6 of CITIT21");
        do
        {
            System.out.print("agps> ");

            String input = scanner.nextLine();
            ArrayList<String> args = new ArrayList<>(Arrays.asList(input.split(commandSeparator)));

            String command = args.get(0);
            if (command.equals("help"))
            {
                args.remove(0);
                System.out.println(cli.help(args));
            }
            else
            {
                finish = command.equals("exit");
                System.out.println(cli.exec(args));
            }

            System.out.println();

        } while (!finish);

        scanner.close();
    }

    private static CommandHandler systemCommandHandler(UserController userController, SocialPlanController socialPlanController)
    {
        CommandHandler handler = new CommandHandler("System commands");

        handler.addCommand("user", userCommandHandler(userController));
        handler.addCommand("social-plan", socialPlanCommandHandler(socialPlanController));
        handler.addCommand("exit", new ExitCommand());

        return handler;
    }

    private static CommandHandler userCommandHandler(UserController controller)
    {
        CommandHandler handler = new CommandHandler("User commands");

        handler.addCommand("register", new RegisterUserCommand(controller));
        handler.addCommand("login", new LoginUserCommand(controller));
        handler.addCommand("logout", new LogoutUserCommand(controller));

        return handler;
    }

    private static CommandHandler socialPlanCommandHandler(SocialPlanController controller)
    {
        CommandHandler handler = new CommandHandler("Social plan commands");

        handler.addCommand("create", new CreateSocialPlanCommand(controller));
        handler.addCommand("delete", new DeleteSocialPlanCommand(controller));
        handler.addCommand("add-activity", new AddActivityCommand(controller));
        handler.addCommand("check-cost", new CheckPlanCostCommand(controller));
        handler.addCommand("join", new JoinSocialPlanCommand(controller));
        handler.addCommand("unjoin", new UnjoinSocialPlanCommand(controller));
        handler.addCommand("list", new ListSocialPlansCommand(controller));
        handler.addCommand("list-subscribed", new ListSuscribedSocialPlansCommand(controller));
        handler.addCommand("set-score", new ScoreSocialPlanCommand(controller));
        handler.addCommand("show-activities", new ShowSocialPlanActivitiesCommand(controller));
        handler.addCommand("show-participants", new ShowParticipantsCommand(controller));
        handler.addCommand("show-duration", new ShowSocialPlanDurationCommand(controller));
        handler.addCommand("set-capacity", new SetSocialPlanCapacityCommand(controller));

        return handler;
    }

    private static void mockData(UserRepository userRepository, SocialPlanRepository socialPlanRepository)
    {
        UserService userService = new UserService(userRepository);

        try
        {
            userService.registerUser("Miguel", "miguel", LocalDate.now().minusYears(80), "123456789");
            userService.registerUser("Ibai", "ibai", LocalDate.now().minusYears(45), "987654321");
            userService.registerUser("Jorge", "jorge", LocalDate.now().minusYears(20), "555555555");
        }
        catch (InvalidAgeException e)
        {
            throw new RuntimeException(e);
        }
        catch (PhoneNumberAlreadyAddedException e)
        {
            throw new RuntimeException(e);
        }
        catch (InvalidPhoneNumberException e)
        {
            throw new RuntimeException(e);
        }
        catch (InvalidPasswordException e)
        {
            throw new RuntimeException(e);
        }
        catch (UserAlreadyAddedException e)
        {
            throw new RuntimeException(e);
        }

        try
        {
            Clock pastClock = Clock.offset(Clock.systemDefaultZone(), Duration.ZERO.minusDays(50));
            SocialPlanService socialPlanService = new SocialPlanService(socialPlanRepository, pastClock);

            socialPlanService.createSocialPlan("Ibai", "Films", LocalDateTime.now(pastClock).plusMinutes(5), "La Gavia", OptionalInt.empty());
            socialPlanService.addActivity(new SocialPlanId("Ibai", "Films"), "Star-Wars-1", "Watch_first_Star_Wars_film", 130, 9.0, ActivityType.CINEMA, OptionalInt.of(5));
            socialPlanService.addActivity(new SocialPlanId("Ibai", "Films"), "Star-Wars-2", "Watch_second_Star_Wars_film", 100, 9.0, ActivityType.CINEMA, OptionalInt.of(3));

            socialPlanService.joinSocialPlan(new SocialPlanId("Ibai", "Films"), "Miguel");
            socialPlanService.joinSocialPlan(new SocialPlanId("Ibai", "Films"), "Ibai");
            socialPlanService.joinSocialPlan(new SocialPlanId("Ibai", "Films"), "Jorge");

            socialPlanService.createSocialPlan("Jorge", "Park", LocalDateTime.now(pastClock).plusDays(1), "Retiro_park", OptionalInt.of(30));
            socialPlanService.addActivity(new SocialPlanId("Jorge", "Park"), "Stroll", "Stroll_through_Retiro", 45, 0, ActivityType.GENERIC, OptionalInt.empty());

            socialPlanService.joinSocialPlan(new SocialPlanId("Jorge", "Park"), "Jorge");
            socialPlanService.joinSocialPlan(new SocialPlanId("Jorge", "Park"), "Ibai");
            socialPlanService.joinSocialPlan(new SocialPlanId("Jorge", "Park"), "Miguel");

            socialPlanService.createSocialPlan("Miguel", "Theatre", LocalDateTime.now(pastClock).plusDays(3), "Sol", OptionalInt.empty());
            socialPlanService.addActivity(new SocialPlanId("Miguel", "Theatre"), "Meetup", "Meetup_before_theatre", 15, 0, ActivityType.GENERIC, OptionalInt.empty());
            socialPlanService.addActivity(new SocialPlanId("Miguel", "Theatre"), "Hamlet", "Watch_Hamlet", 90, 20, ActivityType.THEATRE, OptionalInt.of(50));

            socialPlanService.joinSocialPlan(new SocialPlanId("Miguel", "Theatre"), "Jorge");
            socialPlanService.joinSocialPlan(new SocialPlanId("Miguel", "Theatre"), "Ibai");

            Clock futureClock = Clock.offset(Clock.systemDefaultZone(), Duration.ZERO.plusDays(50));
            socialPlanService = new SocialPlanService(socialPlanRepository, futureClock);

            socialPlanService.createSocialPlan("Miguel", "Eat", LocalDateTime.now(futureClock).plusMinutes(4), "Sol", OptionalInt.empty());
            socialPlanService.addActivity(new SocialPlanId("Miguel", "Eat"), "Restaurant", "Eat", 30, 19.0, ActivityType.CINEMA, OptionalInt.empty());

            socialPlanService.createSocialPlan("Ibai", "Films-2", LocalDateTime.now(futureClock).plusMinutes(5), "La Gavia", OptionalInt.empty());
            socialPlanService.addActivity(new SocialPlanId("Ibai", "Films-2"), "Star-Wars-3", "Watch_third_Star_Wars_film", 130, 9.0, ActivityType.CINEMA, OptionalInt.of(5));
            socialPlanService.addActivity(new SocialPlanId("Ibai", "Films-2"), "Star-Wars-4", "Watch_fourth_Star_Wars_film", 100, 9.0, ActivityType.CINEMA, OptionalInt.of(2));

            socialPlanService.joinSocialPlan(new SocialPlanId("Ibai", "Films-2"), "Ibai");
            socialPlanService.joinSocialPlan(new SocialPlanId("Ibai", "Films-2"), "Jorge");

            socialPlanService.scoreSocialPlan(new SocialPlanId("Ibai", "Films"), "Miguel", OptionalInt.of(7));
            socialPlanService.scoreSocialPlan(new SocialPlanId("Ibai", "Films"), "Ibai", OptionalInt.of(9));

            socialPlanService.scoreSocialPlan(new SocialPlanId("Miguel", "Theatre"), "Ibai", OptionalInt.of(3));
        }
        catch (InvalidPriceException e)
        {
            throw new RuntimeException(e);
        }
        catch (PastDateException e)
        {
            throw new RuntimeException(e);
        }
        catch (PastSocialPlanException e)
        {
            throw new RuntimeException(e);
        }
        catch (InvalidScoreException e)
        {
            throw new RuntimeException(e);
        }
        catch (SocialPlanCollisionException e)
        {
            throw new RuntimeException(e);
        }
        catch (SocialPlanNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (ParticipantAlreadyInSocialPlanException e)
        {
            throw new RuntimeException(e);
        }
        catch (ActivityAlreadyInSocialPlanException e)
        {
            throw new RuntimeException(e);
        }
        catch (SocialPlanAlreadyAddedException e)
        {
            throw new RuntimeException(e);
        }
        catch (InvalidDurationException e)
        {
            throw new RuntimeException(e);
        }
        catch (FullSocialPlanException e)
        {
            throw new RuntimeException(e);
        }
        catch (ParticipantNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (FutureSocialPlanException e)
        {
            throw new RuntimeException(e);
        }
        catch (InvalidCapacityException e)
        {
            throw new RuntimeException(e);
        }
    }
}
