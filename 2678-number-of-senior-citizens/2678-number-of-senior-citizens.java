class Solution {
    public int countSeniors(String[] details) {
        int seniourCitizen = 0;

        for(String individualString : details){
            int tensPlace = individualString.charAt(11) - '0';
            int unitPlace = individualString.charAt(12) - '0';

            int age = (tensPlace*10) + unitPlace;

            if(age > 60){
                seniourCitizen++;
            }
        }
        return seniourCitizen;
        
    }
}