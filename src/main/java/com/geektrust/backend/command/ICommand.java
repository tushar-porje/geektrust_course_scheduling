package com.geektrust.backend.command;

import java.util.List;

public interface ICommand {
    void execute(List<String> tokens);
}
