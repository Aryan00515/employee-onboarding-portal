
const API_URL = "/api/employees";

// Register Employee
document.getElementById("employeeForm").addEventListener("submit", async function (event) {

    event.preventDefault();

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const phone = document.getElementById("phone").value.trim();
    const department = document.getElementById("department").value;
    const designation = document.getElementById("designation").value.trim();
    const joiningDate = document.getElementById("joiningDate").value;

    const message = document.getElementById("message");

    // Validation
    if (name.length < 2) {
        message.textContent = "Name must contain at least 2 characters.";
        return;
    }

    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailPattern.test(email)) {
        message.textContent = "Please enter a valid email address.";
        return;
    }

    const phonePattern = /^[0-9]{10}$/;

    if (!phonePattern.test(phone)) {
        message.textContent = "Phone number must contain exactly 10 digits.";
        return;
    }

    if (department === "") {
        message.textContent = "Please select a department.";
        return;
    }

    if (designation.length < 2) {
        message.textContent = "Designation must contain at least 2 characters.";
        return;
    }

    if (joiningDate === "") {
        message.textContent = "Please select a joining date.";
        return;
    }

    const selectedDate = new Date(joiningDate);
    const today = new Date();

    today.setHours(0, 0, 0, 0);

    if (selectedDate < today) {
        message.textContent = "Joining date cannot be in the past.";
        return;
    }

    const employee = {
        name: name,
        email: email,
        phone: phone,
        department: department,
        designation: designation,
        joiningDate: joiningDate
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

        message.textContent =
            "Employee registered successfully! ID: " + data.id;

        document.getElementById("employeeForm").reset();

        loadEmployees();

    } catch (error) {

        console.error(error);

        message.textContent =
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

