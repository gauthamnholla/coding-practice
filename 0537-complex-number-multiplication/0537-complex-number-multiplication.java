class Solution {
    public String complexNumberMultiply(String num1, String num2) {

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();

        int i = 0;

        while (num1.charAt(i) != '+') {
            sb1.append(num1.charAt(i));
            i++;
        }
        i++;

        while (num1.charAt(i) != 'i') {
            sb2.append(num1.charAt(i));
            i++;
        }

        i = 0;
        while (num2.charAt(i) != '+') {
            sb3.append(num2.charAt(i));
            i++;
        }
        i++;

        while (num2.charAt(i) != 'i') {
            sb4.append(num2.charAt(i));
            i++;
        }

        int a = Integer.parseInt(sb1.toString());
        int b = Integer.parseInt(sb2.toString());
        int c = Integer.parseInt(sb3.toString());
        int d = Integer.parseInt(sb4.toString());

        int real = a * c - b * d;
        int imag = a * d + b * c;

        return real + "+" + imag + "i";
    }
}