# Fill_Google_Form

Activity

1. Navigate to this google form.


2. Fill in Crio Learner in the 1st text box


3. Write down "I want to be the best QA Engineer! 1710572021'' where 1710572021 is variable - needs to be the current epoch time.


4. Enter your Automation Testing experience in the next radio button


5. Select Java, Selenium and TestNG from the next check-box


6. Provide how you would like to be addressed in the next dropdown


7. Provided the current date minus 7 days in the next date field, it should be dynamically calculated and not hardcoded.


8. Provide the time 07:30 in the next field (Can also be in 24 hour clock)


9. Submit the form
10. You will see a success message on the website. Print the same message on the console upon successful completion

Google Form Challenges
----------------------
1. When we are entering the text in field that automitically some time start entering text from next field (we can use thread.sleep() as there is delay in loading the page)
2. Need to Exclude some options from dropdown we can use not contains in xpath (  //div[@class="dropdown"]//span[not(contains(text(),'Choose'))];  )
3. Selecting the Date which if it is enter text then when we need to select date 7 days before the current date
   (get n days before current date by below method :
public static void selectDateBeforeDaysFromToday(ChromeDriver driver, By by, int days) {
        LocalDateTime date = LocalDateTime.now().minusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(formatter);
        WebElement dateField = driver.findElement(by);
        dateField.clear();
        dateField.sendKeys(formattedDate);
    }
   )
5. Selecting date which if it is we need to select from calender then we need to first click on year first then month and then date
   





