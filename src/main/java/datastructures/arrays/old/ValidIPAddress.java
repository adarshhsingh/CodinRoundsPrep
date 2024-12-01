package datastructures.arrays.old;

public class ValidIPAddress {

    public static void main(String[] args) {
        ValidIPAddress obj = new ValidIPAddress();
        String inp[] = {"20EE:FGb8:85a3:0:0:8A2E:0370:7334","12..33.4","172.16.254.1", "2001:0db8:85a3:0:0:8A2E:0370:7334", "256.256.256.256","2001:0db8:85a3:0:0:8A2E:0370:7334:"};
        for (String in: inp) {
            System.out.println(obj.validIPAddress(in));
        }
    }

    public String validIPAddress(String queryIP) {
        // base case
        // constraint: queryIP consists only of English letters, digits and the characters '.' and ':'.
        boolean isIPV4 = true;
        boolean isIPV6 = true;
        if (queryIP.length() == 0) return "Neither";

        if(queryIP.indexOf('.') != -1 && queryIP.indexOf(':') != -1) {
            //System.out.println("got both . and :");
            return "Neither";
        }

        if(queryIP.indexOf('.') != -1 && queryIP.lastIndexOf('.') != queryIP.length()-1) {
            //System.out.println("checking for ipV4 "+ queryIP);
            // we will check for ipV4
            String extract[] = queryIP.split("\\.");
            //System.out.println(extract[0]);
            if(extract.length == 4) {
               // System.out.println("ipV4 length is right - 4");
                for (String each : extract) {
                    if(each.isBlank() || (each.charAt(0) == '0' && each.length()>1) || each.matches("^\\d+")) {
                       // System.out.println("found leading 0 in ipV4");
                        isIPV4 = false;
                    }
                    try {
                        int x = Integer.parseInt(each);
                        if(x > 255) isIPV4 = false;
                    } catch (Exception e) {
                       // System.out.println("couldn't get int");
                        isIPV4 = false;
                    }
                }
                return (isIPV4)?"IPv4":"Neither";
            }
        }

        if(queryIP.indexOf(':') != -1 && queryIP.lastIndexOf(':') != queryIP.length()-1) {
           // System.out.println("checking for ipV6");
            // we will check for ipV6
            String split[] = queryIP.split(":");
            if(split.length == 8) {
               // System.out.println("ipV6 length is right - 8");
                for (String each : split) {
                    //System.out.println(each);
                    if(each.isBlank() || each.trim().length() == 0 || each.trim().length() > 4 ) isIPV6 = false;
                    char x[] = each.toCharArray();
                    for( char eachchar : x) {
                        if (!((eachchar >= 'A' && eachchar <= 'F') || (eachchar >= 'a' && eachchar <= 'f') || (eachchar >= '0' && eachchar <= '9'))) {
                            isIPV6 = false;
                        }
                    }
                }
                return (isIPV6)?"IPv6":"Neither";
            }
        }

        return "Neither";
    }
}
