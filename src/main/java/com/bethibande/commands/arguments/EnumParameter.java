package com.bethibande.commands.arguments;

import com.bethibande.commands.Parameter;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class EnumParameter<T extends Enum> extends Parameter<T> {

    private final Class<T> type;

    public EnumParameter(final @NotNull String name, final Class<T> type) {
        super(name, s -> EnumParameter.toEnum(s, type));

        this.type = type;

        setAllowedValues(() -> Stream.of(type.getEnumConstants()).map(Enum::name).toArray(String[]::new));
        setValueValidator(this::validate);
    }

    private boolean validate(final String s) {
        for(T constant : type.getEnumConstants()) {
            if(constant.name().equalsIgnoreCase(s)) return true;
        }
        return false;
    }

    private static <T extends Enum> T toEnum(final String s, Class<T> type) {
        for(T constant : type.getEnumConstants()) {
            if(constant.name().equalsIgnoreCase(s)) return constant;
        }
        return null;
    }

}
