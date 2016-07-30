package mayankbatra.andro.idroid;

/**
 * Created by user on 13-03-2016.
 */
public class extract_new_data {

    int i=31;
    String name="",number="",location="",course="",email="",need_name="";

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
        while (Message_Body.charAt(i)!='\n')
        {
            email=email+Message_Body.charAt(i);
            i++;
        }
        return email;
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
        while (Message_Body.charAt(i)!='\n')
        {
            need_name=need_name+Message_Body.charAt(i);
            i++;
        }
        return need_name;
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

            i++;
        }
        while (Message_Body.charAt(i)!=':')
        {
            i++;
        }
        i++;
        while (i<=length)
        {
            course=course+Message_Body.charAt(i);
            i++;
        }
        return course;
    }
}
