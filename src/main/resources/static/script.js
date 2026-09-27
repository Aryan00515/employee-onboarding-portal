```javascript
const API_URL = "/api/employees";

// Register Employee
document.getElementById("employeeForm").addEventListener("submit", async function (event) {

    event.preventDefault();

    const employee = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
        department: document.getElementById("department").value,
        designation: document.getElementById("designation").value,
        joiningDate: document.getElementById("joiningDate").value
    };

    try {

        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(employee)
        });

        if (!response.ok) {
            throw new Error("Registration failed");
        }

        const data = await response.json();

        document.getElementById("message").textContent =
            "Employee registered successfully! ID: " + data.id;

        document.getElementById("employeeForm").reset();

        loadEmployees();

    } catch (error) {

        console.error(error);

        document.getElementById("message").textContent =
            "Error registering employee.";

    }

});


// Load Employees
async function loadEmployees() {

    try {

        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Failed to load employees");
        }

        const employees = await response.json();

        console.log("Employees:", employees);

        const tableBody = document.getElementById("employeeTableBody");

        tableBody.innerHTML = "";

        employees.forEach(function (employee) {

            const row = document.createElement("tr");

            row.innerHTML =
                "<td>" + employee.id + "</td>" +
                "<td>" + employee.name + "</td>" +
                "<td>" + employee.email + "</td>" +
                "<td>" + employee.department + "</td>" +
                "<td>" + employee.designation + "</td>" +
                "<td>" + employee.joiningDate + "</td>" +
                "<td>" + employee.status + "</td>" +
                "<td>" +
                    "<button class='approve-btn' onclick='approveEmployee(" + employee.id + ")'>" +
                        "Approve" +
                    "</button> " +
                    "<button class='reject-btn' onclick='rejectEmployee(" + employee.id + ")'>" +
                        "Reject" +
                    "</button>" +
                "</td>";

            tableBody.appendChild(row);

        });

    } catch (error) {

        console.error("Error loading employees:", error);

    }

}


// Approve Employee
async function approveEmployee(id) {

    try {

        const response = await fetch(
            API_URL + "/" + id + "/approve",
            {
                method: "PUT"
            }
        );

        if (!response.ok) {
            throw new Error("Approval failed");
        }

        await response.json();

        loadEmployees();

    } catch (error) {

        console.error(error);

        alert("Unable to approve employee.");

    }

}


// Reject Employee
async function rejectEmployee(id) {

    try {

        const response = await fetch(
            API_URL + "/" + id + "/reject",
            {
                method: "PUT"
            }
        );

        if (!response.ok) {
            throw new Error("Rejection failed");
        }

        await response.json();

        loadEmployees();

    } catch (error) {

        console.error(error);

        alert("Unable to reject employee.");

    }

}


// Load employees when page opens
window.addEventListener("DOMContentLoaded", function () {

    loadEmployees();

});
```
