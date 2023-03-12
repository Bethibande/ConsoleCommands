package com.bethibande.commands;

import java.util.Map;

public record CommandParseResult(Map<String, Object> parameterValues, Map<String, Object[]> argumentValues) {
}
