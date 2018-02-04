package by.gurinovich.webproject.command;

import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    Router execute(HttpServletRequest request) throws CommandException;
}