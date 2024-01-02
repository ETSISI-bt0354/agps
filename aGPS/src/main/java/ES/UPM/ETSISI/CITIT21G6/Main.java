package ES.UPM.ETSISI.CITIT21G6;

import ES.UPM.ETSISI.CITIT21G6.CLI.CommandHandler;
import ES.UPM.ETSISI.CITIT21G6.CLI.ExitCommand;
import ES.UPM.ETSISI.CITIT21G6.CLI.socialPlanCommand.*;
import ES.UPM.ETSISI.CITIT21G6.CLI.userCommand.LoginUserCommand;
import ES.UPM.ETSISI.CITIT21G6.CLI.userCommand.LogoutUserCommand;
import ES.UPM.ETSISI.CITIT21G6.CLI.userCommand.RegisterUserCommand;
import ES.UPM.ETSISI.CITIT21G6.controller.SocialPlanController;
import ES.UPM.ETSISI.CITIT21G6.controller.UserController;
import ES.UPM.ETSISI.CITIT21G6.repository.InMemorySocialPlanRepository;
import ES.UPM.ETSISI.CITIT21G6.repository.InMemoryUserRepository;
import ES.UPM.ETSISI.CITIT21G6.service.SocialPlanService;
import ES.UPM.ETSISI.CITIT21G6.service.UserService;
import ES.UPM.ETSISI.CITIT21G6.view.CLISocialPlanView;
import ES.UPM.ETSISI.CITIT21G6.view.CLIUserView;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] argv)
    {
        UserController userController = new UserController(new UserService(new InMemoryUserRepository()), new CLIUserView());
        SocialPlanController socialPlanController = new SocialPlanController(new SocialPlanService(new InMemorySocialPlanRepository(), Clock.systemDefaultZone()), new CLISocialPlanView());

        CommandHandler cli = systemCommandHandler(userController, socialPlanController);

        Scanner scanner = new Scanner(System.in);
        boolean finish = false;

        do
        {
            System.out.print("agps> ");
            String input = scanner.nextLine();
            ArrayList<String> args = new ArrayList<>(Arrays.asList(input.split(" ")));
            String command = args.get(0);
            if (command.equals("exit"))
                finish = true;

            System.out.println(cli.exec(args));
            System.out.println();

        } while (!finish);

        scanner.close();
    }

    public static CommandHandler systemCommandHandler(UserController userController, SocialPlanController socialPlanController)
    {
        CommandHandler handler = new CommandHandler("System commands");

        handler.addCommand("user", userCommandHandler(userController));
        handler.addCommand("social-plan", socialPlanCommandHandler(socialPlanController));
        handler.addCommand("exit", new ExitCommand());

        return handler;
    }

    public static CommandHandler userCommandHandler(UserController controller)
    {
        CommandHandler handler = new CommandHandler("User commands");

        handler.addCommand("register", new RegisterUserCommand(controller));
        handler.addCommand("login", new LoginUserCommand(controller));
        handler.addCommand("logout", new LogoutUserCommand(controller));

        return handler;
    }

    public static CommandHandler socialPlanCommandHandler(SocialPlanController controller)
    {
        CommandHandler handler = new CommandHandler("Social plan commands");

        handler.addCommand("create", new CreateSocialPlanCommand(controller));
        handler.addCommand("delete", new DeleteSocialPlanCommand(controller));
        handler.addCommand("add-activity", new AddActivityCommand(controller));
        handler.addCommand("check-cost", new CheckPlanCostCommand(controller));
        handler.addCommand("join", new JoinSocialPlanCommand(controller));
        handler.addCommand("unjoin", new UnjoinSocialPlanCommand(controller));
        handler.addCommand("list", new ListSocialPlandCommand(controller));
        handler.addCommand("list-subscribed", new ListSuscribedSocialPlanCommand(controller));
        handler.addCommand("set-score", new ScoreSocialPlanCommand(controller));
        handler.addCommand("show-activities", new ShowSocialPlanActivitiesCommand(controller));
        handler.addCommand("show-participants", new ShowParticipantsCommand(controller));
        handler.addCommand("show-duration", new ShowSocialPlanDurationCommand(controller));
        handler.addCommand("set-capacity", new SetSocialPlanCapacityCommand(controller));

        return handler;
    }
}
