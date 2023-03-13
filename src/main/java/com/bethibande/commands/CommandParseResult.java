package com.bethibande.commands;

import java.util.HashMap;
import java.util.Map;

public record CommandParseResult(HashMap<String, Object> parameterValues, HashMap<String, Object[]> argumentValues) {
}
