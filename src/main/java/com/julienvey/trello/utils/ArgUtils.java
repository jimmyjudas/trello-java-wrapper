package com.julienvey.trello.utils;

import com.julienvey.trello.domain.Argument;

public class ArgUtils {
    private static final Argument[] NO_ARGUMENTS = new Argument[0];

    private ArgUtils() {
    }

    public static Argument arg(String argName, String argValue) {
        return new Argument(argName, argValue);
    }

    public static Argument[] args(String argName1, String argValue1, String argName2, String argValue2) {
        return new Argument[]{
                new Argument(argName1, argValue1),
                new Argument(argName2, argValue2),
        };
    }

    public static Argument[] addToArgsStart(String argName, String argValue, Argument... otherArgs)
    {
        Argument[] newArgs = new Argument[otherArgs.length + 1];
        newArgs[0] = new Argument(argName, argValue);
        System.arraycopy(otherArgs, 0, newArgs, 1, otherArgs.length);
        return newArgs;
    }

    public static Argument[] addToArgsEnd(String argName, String argValue, Argument... otherArgs)
    {
        Argument[] newArgs = new Argument[otherArgs.length + 1];
        System.arraycopy(otherArgs, 0, newArgs, 0, otherArgs.length);
        newArgs[otherArgs.length] = new Argument(argName, argValue);
        return newArgs;
    }

    /**
     * Factory method to easily create the {@link Argument} array. Parameters at the odd positions are names, at even
     * positions are values.
     * <p>
     * For examples following test will pass:
     *
     * <pre>
     * <code>
     * Argument[] args = ArgUtils.args(
     *         "name1", "value1",
     *         "name2", "value2",
     *         "name3", "value3",
     *         "name4", "value4"
     * );
     *
     * assertThat(Arrays.asList(args))
     *         .containsExactly(
     *                 new Argument("name1", "value1"),
     *                 new Argument("name2", "value2"),
     *                 new Argument("name3", "value3"),
     *                 new Argument("name4", "value4")
     *         );
     * </code>
     * </pre>
     *
     * @param nameValuePairs The name value pairs.
     * @throws IllegalArgumentException When {@code nameValuePairs} is odd.
     * @return The arguments.
     */
    public static Argument[] args(String... nameValuePairs) {
        int length = nameValuePairs.length;

        if (length == 0) {
            return NO_ARGUMENTS;
        }

        // even number of arguments ?
        if ((length & 1) != 0) {
            throw new IllegalArgumentException("Expecting even number of arguments, but got: " + length);
        }
        // allocating array by a half of an input
        Argument[] args = new Argument[length >> 1];

        for (int i = 0, j = 0; i < args.length; i++) {
            args[i] = arg(nameValuePairs[j++], nameValuePairs[j++]);
        }

        return args;
    }
}
