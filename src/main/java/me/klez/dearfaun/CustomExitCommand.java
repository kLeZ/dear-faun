package me.klez.dearfaun;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomExitCommand implements Quit.Command {
	ApplicationContext ctx;

	@ShellMethod(value = "Exit the shell.", key = { "quit", "exit" })
	public void quit() {
		System.exit(SpringApplication.exit(ctx));
	}
}
