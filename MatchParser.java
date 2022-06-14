public class MatchParser {
    public MatchParser(){}

    public double Parse(String s) throws Exception
    {
        laba4.Result result = PlusMinus(s);
        if (!result.rest.isEmpty()) {
            System.err.println("Ошибка! Не удалось найти соответствие ");
            System.err.println("А именно: " + result.rest);
        }
        return result.acc;
    }

    private laba4.Result PlusMinus(String s) throws Exception
    {
        laba4.Result current = Num(s);
        double acc = current.acc;

        while (current.rest.length() > 0) {
            if (!(current.rest.charAt(0) == '+' || current.rest.charAt(0) == '-')) break;

            char sign = current.rest.charAt(0);
            String next = current.rest.substring(1);

            acc = current.acc;

            current = Num(next);
            if (sign == '+') {
                acc += current.acc;
            } else {
                acc -= current.acc;
            }
            current.acc = acc;
        }
        return new laba4.Result(current.acc, current.rest);
    }

    private laba4.Result Num(String s) throws Exception
    {
        int i = 0;
        int dot_cnt = 0;
        boolean negative = false;
        if( s.charAt(0) == '-' ){
            negative = true;
            s = s.substring( 1 );
        }
        while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
            if (s.charAt(i) == '.' && ++dot_cnt > 1) {
                throw new Exception("недопустимый номер '" + s.substring(0, i + 1) + "'");
            }
            i++;
        }
        if( i == 0 ){
            throw new Exception( "не удается получить действительный номер  '" + s + "'" );
        }

        double dPart = Double.parseDouble(s.substring(0, i));
        if( negative ) dPart = -dPart;
        String restPart = s.substring(i);

        return new laba4.Result(dPart, restPart);
    }
}
