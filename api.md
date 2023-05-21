# MindMentor API Documentation

## Course

### **Endpoint:** `/course/create`

### Request Body:

```
{
    "OrganizationId": <integer>,
    "CourseName": <string>,
    "CourseCategoryId": <integer>,
    "CourseDescription": <string>,
    "CourseDuration": <string>,
    "CourseLevel": <string>,
    "CourseFees": <integer>,
    "Enrollment": <integer>,
    "Prerequites": <string>,
    "InstructorId": <integer>,
    "CourseFormat": <string>,
    "StartDate": <date>,
    "EndDate": <date>
}
```

### Response:

```
{
    "StatusCode": 200,
    "Message": "Course created successfully",
    "Data": null
}
```

### **Endpoint:** `/course/getCourseDetails/{id}`

### Request:

```
None
```

### Response:

```
{
    "StatusCode": 200,
    "Message": "Course details fetched successfully",
    "Data": [
        {
            "CourseId": <integer>,
            "CourseName": <string>,
            "CourseCategoryId": <integer>,
            "CourseDescription": <string>,
            "CourseDuration": <string>,
            "CourseLevel": <string>,
            "CourseFees": <integer>,
            "Enrollment": <integer>,
            "Prerequites": <string>,
            "InstructorId": <integer>,
            "CourseFormat": <string>,
            "StartDate": <date>,
            "EndDate": <date>
        }
    ]
}
```

### **Endpoint:** `/course/getCourseList/all/{organizationId}`

### Request:

```
None
```

### Response:

```
{
    "StatusCode": 200,
    "Message": "Course details fetched successfully",
    "Data": [
        {
            "CourseId": <integer>,
            "CourseName": <string>,
            "CourseDuration": <string>,
            "CourseLevel": <string>,
            "CourseCategoryName": <string>,
            "InstructorName": <string>,
            "CourseFormat": <string>,
            "CourseFee": <integer>
        },
        {
            "CourseId": <integer>,
            "CourseName": <string>,
            "CourseDuration": <string>,
            "CourseLevel": <string>,
            "CourseCategoryName": <string>,
            "InstructorName": <string>,
            "CourseFormat": <string>,
            "CourseFee": <integer>
        }
    ]
}
```

### **Endpoint:** `/course/getCategoryAndCourseCount/all/{organizationId}`

### Request:

```
None
```

### Response:

```
{
    "StatusCode": 200,
    "Message": "Course details fetched successfully",
    "Data": [
        {
            "CourseCategoryName": <string>,
            "CourseCount": <integer>
        },
        {
            "CourseCategoryName": <string>,
            "CourseCount": <integer>
        }
    ]
}
```

# User

### **Endpoint:** `/User/create`

**Body:**

```
{
    "OrganizationId": [integer],
    "RoleId": [integer],
    "StateId": [integer],
    "CityId": [integer],
    "Address": [string],
    "EmailAddress": [string],
    "Password": [string],
    "FirstName": [string],
    "LastName": [string],
    "PhoneNumber": [string],
    "Gender": [string],
    "DateOfBirth": [date],
    "JoiningDate": [date]
}
```

**Response:**

```
{
    "StatusCode": 200,
    "Message": "User created successfully",
    "Data": null
}
```

### **Endpoint:** `/User/getUserDetails/{userId}`

**Parameters:**

- `userId`: [integer] The ID of the user.

**Response:**

```
{
    "StatusCode": 200,
    "Message": "User details fetched successfully",
    "Data": [
        {
            "RoleId": [integer],
            "StateId": [integer],
            "CityId": [integer],
            "Address": [string],
            "EmailAddress": [string],
            "FirstName": [string],
            "LastName": [string],
            "PhoneNumber": [string],
            "Gender": [string],
            "DateOfBirth": [date],
            "JoiningDate": [date]
        }
    ]
}
```

### **Endpoint:** `/User/getUserList/all/{OrganizationId}`

**Parameters:**

- `OrganizationId`: [integer] The ID of the organization.

**Response:**

```
{
    "StatusCode": 200,
    "Message": "User details fetched successfully",
    "Data": [
        {
            "UserId": [integer],
            "Name": [string],
            "Email": [string],
            "RoleName": [string],
            "JoiningDate": [date]
        },
        {
            "UserId": [integer],
            "Name": [string],
            "Email": [string],
            "RoleName": [string],
            "JoiningDate": [date]
        }
    ]
}
```

### **Endpoint:** `/User/getRoleAndUserCount/all/{organizationId}`

**Parameters:**

- `organizationId`: [integer] The ID of the organization.

**Response:**

This API will fetch all the UserRoles and User count in that Role.

```
{
    "StatusCode": 200,
    "Message": "Course details fetched successfully",
    "Data": [
        {
            "CourseCategoryName": <string>,
            "CourseCount": <integer>
        },
        {
            "CourseCategoryName": <string>,
            "CourseCount": <integer>
        }
    ]
}
```

## Schedule

### **Endpoint:** `/schedule/create/`

**Request Body:**

```
{
    "organizationId": <integer>,
    "date": <date>,
    "Time": <time>,
    "Duration": <time>,
    "CourseId": <integer>,
    "InstructorId": <integer>
}
```

**Response:**

```
{
    "StatusCode": 200,
    "Message": "Session created successfully",
    "Data": null
}
```

### **Endpoint:** `/schedule/sessions/all/{organizationId}`

**Request Body:**

```
None
```

**Response:**

```
{
    "StatusCode": 200,
    "Message": "Session details fetched successfully",
    "Data": [
        {
            "SessionId": <integer>,
            "CourseName": <string>,
            "Date": <date>,
            "Time": <time>,
            "Duration": <time>,
            "InstructorName": <string>
        },
        {
            "SessionId": <integer>,
            "CourseName": <string>,
            "Date": <date>,
            "Time": <time>,
            "Duration": <time>,
            "InstructorName": <string>
        }
    ]
}
```

### **Endpoint:** `/schedule/getSession/{sessionId}`

**Request Body:**

```
None
```

**Response:**

```
{
    "StatusCode": 200,
    "Message": "Sessions fetched successfully",
    "Data": [
        {
            "Id": <integer>,
            "date": <date>,
            "Time": <time>,
            "Duration": <time>,
            "CourseName": <string>,
            "InstructorName": <string>
        }
    ]
}
```

## Common

### **Endpoint:** `/course/getAllCourseIdAndName/{organizationId}`

### Request:

```
None
```

### Response:

```
{
    "StatusCode": 200,
    "Message": "Course created successfully",
    "Data": [
        {
            "CourseId": 1,
            "CourseName": "Physics"
        },
        {
            "CourseId": 2,
            "CourseName": "Chemistry"
        }
    ]
}
```

### **Endpoint:** `/User/getAllUserIdAndFirstNameAndLastName/{OrganizationId}/{roleId}`

**Parameters:**

- `OrganizationId`: [integer] The ID of the organization.
- `roleId`: [integer] The ID of the role.

**Response:**

```
{
    "StatusCode": 200,
    "Message": "User created successfully",
    "Data": [
        {
            "UserId": [integer],
            "FirstName": [string],
            "LastName": [string]
        },
        {
            "UserId": [integer],
            "FirstName": [string],
            "LastName": [string]
        }
    ]
}
```

### **Get all states**

### **Endpoint:** `common/getAllStates`

**Request:** null

**Response:**

```json
{
  "status": "Success",
  "statusCode": 200,
  "message": "states fetched successfully",
  "data": [
    {
      "stateId": "",
      "stateName": ""
    },
    {
      "stateId": "",
      "stateName": ""
    }
  ]
}
```

### getAllCities

### **Endpoint:** `/common/getAllCities`

**Request:** null

**Response:**

```json
{
  "status": "Success",
  "statusCode": 200,
  "message": "Cities fetched successfully",
  "data": [
    {
      "cityId": "",
      "cityName": "",
      "stateId": ""
    },
    {
      "cityId": "",
      "cityName": "",
      "stateId": ""
    }
  ]
}
```

### **getMenuAndSubmenu**

### **Endpoint:** `common/getMenuAndSubmenu`

**Request:** null

**Response:**

```json
{
  "status": "Success",
  "statusCode": 200,
  "message": "Menu and submenu fetched successfully",
  "data": {
    "menu": [
      {
        "menu_name": "Courses",
        "menu_link": "/courses",
        "menu_icon": "fa fa-book",
        "submenus": [
          {
            "sub_menu_name": "All Courses",
            "sub_menu_link": "/courses/all",
            "sub_menu_icon": "fa fa-list"
          },
          {
            "sub_menu_name": "Create new course",
            "sub_menu_link": "/courses/create",
            "sub_menu_icon": "fa fa-plus"
          },
          {
            "sub_menu_name": "Course categories",
            "sub_menu_link": "/courses/categories",
            "sub_menu_icon": "fa fa-tags"
          }
        ]
      },
      {
        "menu_name": "Users",
        "menu_link": "/users",
        "menu_icon": "fa fa-users",
        "submenus": [
          {
            "sub_menu_name": "All Users",
            "sub_menu_link": "/users/all",
            "sub_menu_icon": "fa fa-list"
          },
          {
            "sub_menu_name": "Add new user",
            "sub_menu_link": "/users/create",
            "sub_menu_icon": "fa fa-plus"
          }
        ]
      },
      {
        "menu_name": "Schedule",
        "menu_link": "/schedule",
        "menu_icon": "fa fa-calendar-alt",
        "submenus": [
          {
            "sub_menu_name": "Coaching sessions",
            "sub_menu_link": "/schedule/sessions",
            "sub_menu_icon": "fa fa-calendar"
          },
          {
            "sub_menu_name": "Availability calendar",
            "sub_menu_link": "/schedule/calendar",
            "sub_menu_icon": "far fa-clock"
          },
          {
            "sub_menu_name": "Create Session",
            "sub_menu_link": "/schedule/create",
            "sub_menu_icon": "fa fa-book"
          }
        ]
      },
      {
        "menu_name": "Assignment",
        "menu_link": "/assignment",
        "menu_icon": "fas fa-file-alt",
        "submenus": [
          {
            "sub_menu_name": "Create New",
            "sub_menu_link": "/assignment/create_new",
            "sub_menu_icon": "fas fa-plus"
          },
          {
            "sub_menu_name": "View Assignments",
            "sub_menu_link": "/assignment/view_assignments",
            "sub_menu_icon": "fas fa-tasks"
          }
        ]
      },
      {
        "menu_name": "Feedback",
        "menu_link": "/feedback",
        "menu_icon": "fa fa-chart-pie",
        "submenus": [
          {
            "sub_menu_name": "Create New",
            "sub_menu_link": "/feedback/createNew",
            "sub_menu_icon": "fas fa-comment-medical"
          },
          {
            "sub_menu_name": "View Feedback",
            "sub_menu_link": "/feedback/viewFeedback",
            "sub_menu_icon": "fas fa-comment-dots"
          }
        ]
      }
    ]
  }
}
```

### **createFeedback**

### **Endpoint:** `feedback/create`

**Request:**

```json
{
  "organizationId"
  "instructorId": ,
  "courseId": ,
  "rating": ,
  "review":
}
```

**Response:**

```json
{
  "status": "Success",
  "statusCode": 200,
  "message": "Thanks for your feedback.",
  "data": "Feedback created"
}
```

## **getAllFeedbacks/{organizationId}**

### **Endpoint:** `feedback/getAllFeedbacks/{organizationId}`

**Request:** null

**Response:**

```json
{
  "status": "Success",
  "statusCode": 200,
  "message": "Thanks for your feedback.",
  "data": [
    {
      "feedbackid": ,
      "instructorName": ,
      "review": ,
      "courseName": ,
      "rating":
    },
    {
      "feedbackid": ,
      "instructorName": ,
      "review": ,
      "courseName": ,
      "rating":
    }
  ]
}
```

### **Endpoint:** `feedback/getAllFeedbacks/{instructorId}`

**Request:** null

**Response:**

```json
{
  "status": "Success",
  "statusCode": 200,
  "message": "Feedbacks fetched successfully",
  "data": [
    {
      "feedbackid": ,
      "instructorName": ,
      "review": ,
      "courseName": ,
      "rating":
    },
    {
      "feedbackid": ,
      "instructorName": ,
      "review": ,
      "courseName": ,
      "rating":
    }
  ]
}
```

---

### **getFeedbacks/{feedbackId}**

### **Endpoint:** `feedback/getFeedback/{feedbackId}`

**Request:** null

**Response:**

```json
{
  "status": "Success",
  "statusCode": 200,
  "message": "Fetching feedback successful",
  "data": [
    {
      "feedbackid": ,
      "instructorName": ,
      "review": ,
      "courseName": ,
      "rating":
    }
  ]
}
```

## assignment/create

### **Endpoint:** `assignment/create`

**Request:**

```json
{
  "organizationId": [string],
  "title": [string],
  "courseId": [string],
  "instructorId": [string],
  "maxPoint": [integer],
  "fileTypeAllowed": [string],
  "maxFileSize": [integer],
  "dueDate": [string],
  "fileName": [string],
  "AssignmentType": [string]
}
```

**Response:**

```json
{
  "status": "Success",
  "statusCode": 200,
  "message": "Assignment created",
  "data": "Assignment created"
}
```

---

## getAllAssignments/{organizationId}

### **Endpoint:** `getAllAssignments/{organizationId}`

**Request:** null

**Response:**

```json
{
  "status": "Success",
  "statusCode": 200,
  "message": "Thanks for your feedback.",
  "data": [
    {
      "AssignmentId": [string],
      "Title": [string],
      "instructorName": [string],
      "courseName": [string],
      "DueDate": [string],
      "AssignmentType": [string]
    },
    {
      "AssignmentId": [string],
      "Title": [string],
      "instructorName": [string],
      "courseName": [string],
      "DueDate": [string],
      "AssignmentType": [string]
    }
  ]
}
```

---

## getAllAssignments/{InstructorId}

### **Endpoint:** `getAllAssignments/{InstructorId}`

**Request:** null

**Response:**

```json
{
  "status": "Success",
  "statusCode": 200,
  "message": "Thanks for your feedback.",
  "data": [
    {
      "AssignmentId": [string],
      "Title": [string],
      "instructorName": [string],
      "courseName": [string],
      "DueDate": [string],
      "AssignmentType": [string]
    },
    {
      "AssignmentId": [string],
      "Title": [string],
      "instructorName": [string],
      "courseName": [string],
      "DueDate": [string],
      "AssignmentType": [string]
    }
  ]
}
```

---

## getAllAssignments

### **Endpoint:** `getAllAssignments`

**Request:**

```json
{
  "organisationId": [string],
  "courseId": [string]
}
```

**Response:**

```json
{
  "status": "Success",
  "statusCode": 200,
  "message": "Thanks for your feedback.",
  "data": [
    {
      "AssignmentId": [string],
      "Title": [string],
      "instructorName": [string],
      "courseName": [string],
      "DueDate": [string],
      "AssignmentType": [string]
    },
    {
      "AssignmentId": [string],
      "Title": [string],
      "instructorName": [string],
      "courseName": [string],
      "DueDate": [string],
      "AssignmentType": [string]
    }
  ]
}
```

---

## getAssignment/{AssignmentId}

### **Endpoint:** `getAssignment/{AssignmentId}`

**Request:** null

**Response:**

```json
{
  "status": "Success",
  "statusCode": 200,
  "message": "Fetching feedback successful",
  "data": [
    {
      "assignmentId": [string],
      "title": [string],
      "courseId": [string],
      "instructorId": [string],
      "maxPoint":

 [integer],
      "fileTypeAllowed": [string],
      "maxFileSize": [integer],
      "dueDate": [string],
      "fileName": [string],
      "AssignmentType": [string]
    }
  ]
}
```

## Course

### **Endpoint:** `/course/update/{courseId}`

### Request Body:

```
{
    "CourseName": <string>,
    "CourseCategoryId": <integer>,
    "CourseDescription": <string>,
    "CourseDuration": <string>,
    "CourseLevel": <string>,
    "CourseFee": <integer>,
    "Enrollment": <integer>,
    "Prerequites": <string>,
    "InstructorId": <integer>,
    "CourseFormat": <string>,
    "StartDate": <date>,
    "EndDate": <date>
}
```

### Response:

```
{
    "status":"success",
    "StatusCode": 200,
    "Message": "Course created successfully",
    "Data": null
}
```

## user

### **Endpoint:** `/user/update/{userId}`

### Request Body:

```
{
    "userId":
    "role":"teacher",
    "stateId":"1",
    "cityId":"1",
    "address":"Howrah Maidan",
    "emailAddress":"priya@gmail.com",
    "password":"p@ssword",
    "firstName":"Priya",
    "lastName":"Patel",
    "phoneNumber":"7584691230",
    "gender":"female",
    "dateOfBirth":"07-04-2000",
    "joiningDate":"18-05-2023"
}
```

### Response:

```
{
    "status":"success",
    "StatusCode": 200,
    "Message": "user updated successfully",
    "Data": null
}
```

## feedback

### **Endpoint:** `/feedback/update/{feedbackId}`

### Request Body:

```
{
    "feedbackId":
    "instructorId":"5",
    "courseId":"2",
    "rating":"4",
    "review":"nice teacher"
}
```

### Response:

```
{
    "status":"success",
    "StatusCode": 200,
    "Message": "Feedback updated successfully",
    "Data": null
}
```

## assignment

### **Endpoint:** `/assignment/update/{assignmentId}`

### Request Body:

```
{
  "assignmentId": [number],
  "title": [string],
  "courseId": [string],
  "instructorId": [string],
  "maxPoint": [integer],
  "fileTypeAllowed": [string],
  "maxFileSize": [integer],
  "dueDate": [string],
  "fileName": [string],
  "AssignmentType": [string]
}
```

### Response:

```
{
    "status":"success",
    "StatusCode": 200,
    "Message": "Assignment updated successfully",
    "Data": null
}
```
