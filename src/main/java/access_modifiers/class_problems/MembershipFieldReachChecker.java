
package access_modifiers.class_problems;

public class MembershipFieldReachChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier.equals("private")) {
            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }
            return "DENIED";
        }

        if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }
            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }
            return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {"private", "default", "protected", "public"};
        String result = "";

        for (String modifier : modifiers) {

            int allowed = 0;
            int denied = 0;

            for (String[] attempt : attempts) {

                if (attempt[0].equals(modifier)) {

                    String status = classifyAccess(attempt[0], attempt[1]);

                    if (status.equals("ALLOWED")) {
                        allowed++;
                    } else {
                        denied++;
                    }
                }
            }

            result += modifier + ": "
                    + allowed + " allowed / "
                    + denied + " denied\n";
        }

        return result.trim();
    }

    public static void main(String[] args) {

        System.out.println(
                classifyAccess("private", "SAME_CLASS")
        );

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeByModifier(attempts));
    }
}