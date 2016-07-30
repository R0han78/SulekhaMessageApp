package mayankbatra.andro.idroid;

public class extract_data {

    int i=31;
    String name="",number="",location="",course="",email="",need_name="", locality="";

    public String ret_name(String Message_Body)
    {

        while(Message_Body.charAt(i)!= '\n')
        {
            name=name+Message_Body.charAt(i);
            ++i;
        }
        return name;
    }
    public String ret_number(String Message_Body)
    {
        i=31;
        while(Message_Body.charAt(i)!= '\n')
        {
            ++i;
        }
        i++;
        while (Message_Body.charAt(i)!= '+')
        {
            i++;
        }
        while (Message_Body.charAt(i)!= '\n')
        {
            number=number+Message_Body.charAt(i);
            i++;
        }
        return number;
    }





    public String ret_course(String Message_Body) {
        i = 31;
        int length=Message_Body.length()-1;
        while (Message_Body.charAt(i) != '\n') {
            ++i;
        }
        i++;
        while (Message_Body.charAt(i) != '+') {
            i++;
        }
        while (Message_Body.charAt(i) != '\n') {

            i++;
        }
        i++;
        while (Message_Body.charAt(i) != ':') {
            i++;
        }
        i++;
        /* while (Message_Body.charAt(i)!='\n')
        {
            course=course+Message_Body.charAt(i);
            i++;
        }*/

        while ((Message_Body.charAt(i)!='\n' && i<=length-1))
        {
            course=course+Message_Body.charAt(i);
            i++;
        }
        return course;
    }











    public String ret_email(String Message_Body)
    {
        i=31;
        while(Message_Body.charAt(i)!= '\n')
        {
            ++i;
        }
        i++;
        while (Message_Body.charAt(i)!= '+')
        {
            i++;
        }
        while (Message_Body.charAt(i)!= '\n')
        {
            i++;
        }
        i++;
        while (Message_Body.charAt(i)!=':')
        {
            i++;
        }
        i++;
        while (Message_Body.charAt(i)!= '\n')
        {
            i++;
        }
        i++;
        while (Message_Body.charAt(i)!=':')
        {
            i++;
        }
        i++;

        if(Message_Body.charAt(i) =='E'){
            while (Message_Body.charAt(i)!='\n')
            {
                email=email+Message_Body.charAt(i);
                i++;
            }
            return email;
        }


        /*else
            return "";
        */

        /*
        else{
                int j;
            String update_Message_Body_front = null , update_Message_Body_back=null;
                for(j=0;j<=i;j++) {
                    update_Message_Body_front.charAt(j) = Message_Body.charAt(j);
                }

                 for(j=i+1;j<Message_Body.length();j++) {
                     update_Message_Body_back.charAt(j) = Message_Body.charAt(j);
                 }

                String add_email = "Email:\n";
                String final_Message_Body = update_Message_Body_front.concat(add_email);
                final_Message_Body = final_Message_Body.concat(update_Message_Body_back);

                for(j=0;j<final_Message_Body.length();j++) {
                    Message_Body.charAt(j) = final_Message_Body.charAt(j);
                }
        }*/
        else{
            String update_Message_Body_front = Message_Body.substring(0,i);
            String update_Message_Body_back = Message_Body.substring(i+1,Message_Body.length());
            String add_email = "Email:\n";
            String final_Message_Body = update_Message_Body_front.concat(add_email);
            final_Message_Body = final_Message_Body.concat(update_Message_Body_back);
            Message_Body = final_Message_Body.substring(0,final_Message_Body.length());
            return "";
        }


    }
    public String ret_location(String Message_Body) {
        i = 31;

        while (Message_Body.charAt(i) != '\n') {
            ++i;
        }
        i++;
        while (Message_Body.charAt(i) != '+') {
            i++;
        }
        while (Message_Body.charAt(i) != '\n') {

            i++;
        }
        i++;
        while (Message_Body.charAt(i) != ':') {
            i++;
        }

        i++;
        while (Message_Body.charAt(i) != '\n') {

            i++;
        }
        while (Message_Body.charAt(i)!=':')
        {
            i++;
        }
        i++;
        while (Message_Body.charAt(i)!='\n')
        {
            location=location+Message_Body.charAt(i);
            i++;
        }
        return location;
    }

    public String ret_locality(String Message_Body) {
        i = 31;
        int length=Message_Body.length()-1;
        while (Message_Body.charAt(i) != '\n') {
            ++i;
        }
        i++;
        while (Message_Body.charAt(i) != '+') {
            i++;
        }
        while (Message_Body.charAt(i) != '\n') {

            i++;
        }
        i++;
        while (Message_Body.charAt(i) != ':') {
            i++;
        }

        i++;
        while (Message_Body.charAt(i) != '\n') {

            i++;
        }
        while (Message_Body.charAt(i)!=':')
        {
            i++;
        }
        i++;
        while (Message_Body.charAt(i)!='\n')
        {

            i++;
        }
        while (Message_Body.charAt(i)!=':')
        {
            i++;
        }
        i++;
        while (Message_Body.charAt(i)!='\n')
        {
            locality=locality+Message_Body.charAt(i);
            i++;
        }
        /*
        while (i<=length)
        {
            course=course+Message_Body.charAt(i);
            i++;
        }*/
        return locality;
    }


    public String ret_needname(String Message_Body) {
        i = 31;
        while (Message_Body.charAt(i) != '\n') {
            ++i;
        }
        i++;
        while (Message_Body.charAt(i) != '+') {
            i++;
        }
        while (Message_Body.charAt(i) != '\n') {

            i++;
        }
        i++;
        while (Message_Body.charAt(i) != ':') {
            i++;
        }

        i++;
        while (Message_Body.charAt(i) != '\n') {

            i++;
        }
        while (Message_Body.charAt(i)!=':')
        {
            i++;
        }
        i++;
        while (Message_Body.charAt(i) != '\n') {

            i++;
        }
        while (Message_Body.charAt(i)!=':')
        {
            i++;
        }
        i++;
        while (Message_Body.charAt(i) != '\n') {

            i++;
        }
        while (Message_Body.charAt(i)!=':')
        {
            i++;
        }
        i++;
        while (Message_Body.charAt(i) != '\n') {

            i++;
        }
        while (Message_Body.charAt(i)!=':')
        {
            i++;
        }
        i++;
        while (Message_Body.charAt(i)!='\n')
        {
            need_name=need_name+Message_Body.charAt(i);
            i++;
        }
        return need_name;
    }

}
