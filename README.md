APPRAISAL SYSTEM

Run the Spring Boot Starter Application.
All the data will be populated with H2 in memory database.
Custom Error Handling is done. With Controller Advice.
Unit Test cases are created with code coverage.

Steps to Run
1. Make a Employee Call
   GET call http://localhost:8080/api/employee/1 - Get Employee Appraisal Details.
   If Manager or HR Discussion not done - Error response message is shown.
   If Serving Notice Period - Error response message is shown.
   If Not completed One Year - Error response thrown for not completing 1 Year in org.
   If Employee is not Present, then Error is thrown.



2. Manager can make a decision on a employee after discussing, and give Ratings or PIP or Bonus by passing below request Body.
   POST call http://localhost:8080/api/employee

   Request Body used RATINGS or BONUS or PIP
   {
   "empid":3,
   "isExceptionalemp":false,
   "bonus":0,
   "starrating":2,
   "comments":"",
   "trainingRequired":true,
   "pip":false
   }


   //FOR BONUS pass
    {
      "empid":3,
      "isExceptionalemp":true,
      "bonus":0, //amount within range
      ...
    }

    //FOR RATINGS pass
    {
      "empid":3,
      "starrating":2,
      ...
    }

    //FOR TRAINING or PIP pass
    {
       "empid":3,
       "trainingRequired":true,
       "pip":true
       ...
    }

    ... represent other fields.

   If Manager or HR Discussion not done - Error response message is shown.
   If Employee is not Present, then Error is thrown.


3. If Manager or HR Discussion is Pending, then after discussion with candidate, below get call to be made for employee (id) Provided.
   GET call http://localhost:8080/api/employee/3/managerhrdiscussion

   If Employee is not Present, then Error is thrown.

   After Discussion, step 2 can be performed

