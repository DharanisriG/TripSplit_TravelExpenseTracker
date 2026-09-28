// ===============================
// ADD TRIP
// ===============================

document.getElementById("tripForm").addEventListener("submit", function(event) {

    event.preventDefault();

    const name =
        document.getElementById("tripName").value;

    const description =
        document.getElementById("tripDescription").value;

    fetch("/api/trips", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            name: name,
            description: description
        })

    })

    .then(response => response.json())

    .then(data => {

        document.getElementById("tripMessage").innerText =
            "Trip added successfully! Trip ID: " + data.id;

        document.getElementById("tripForm").reset();

        loadTrips();
		loadTripDropdown();

    })

    .catch(error => {

        document.getElementById("tripMessage").innerText =
            "Error adding trip";

        console.error(error);

    });

});


// ===============================
// LOAD ALL TRIPS
// ===============================

function loadTrips() {

    fetch("/api/trips")
        .then(response => response.json())
        .then(trips => {

            const tripList =
                document.getElementById("tripList");

            tripList.innerHTML = "";

            if (trips.length === 0) {
                tripList.innerHTML = "<p>No trips found.</p>";
                return;
            }

            trips.forEach(trip => {

                const tripDiv =
                    document.createElement("div");

                tripDiv.className = "trip-card";

                tripDiv.innerHTML = `
                    <h2>${trip.name}</h2>

                    <p>${trip.description}</p>

                    <p>
                        <strong>Trip ID:</strong> ${trip.id}
                    </p>

                    <div class="trip-actions">

                        <button
                            class="edit-btn"
                            onclick="editTrip(${trip.id})">
                            Edit
                        </button>

                        <button
                            class="delete-btn"
                            onclick="deleteTrip(${trip.id})">
                            Delete
                        </button>

                    </div>
                `;

                tripList.appendChild(tripDiv);
            });
        })
        .catch(error => {

            console.error(
                "Error loading trips:",
                error
            );

        });
}

function editTrip(id) {

    const newName = prompt("Enter new trip name:");

    if (newName === null) {
        return;
    }

    const newDescription = prompt("Enter new description:");

    if (newDescription === null) {
        return;
    }

    fetch(`/api/trips/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: newName,
            description: newDescription
        })
    })
    .then(response => response.json())
    .then(updatedTrip => {

        alert("Trip updated successfully!");

        loadTrips();
        loadTripDropdown();

    })
    .catch(error => {

        console.error("Error updating trip:", error);

    });
}


function deleteTrip(id) {

    const confirmDelete =
        confirm("Are you sure you want to delete this trip?");

    if (!confirmDelete) {
        return;
    }

    fetch(`/api/trips/${id}`, {
        method: "DELETE"
    })
    .then(response => response.text())
    .then(message => {

        alert(message);

        loadTrips();
        loadTripDropdown();

    })
    .catch(error => {

        console.error("Error deleting trip:", error);

    });
}

// ===============================
// ADD PARTICIPANT
// ===============================

document.getElementById("participantForm")
    .addEventListener("submit", function(event) {

        event.preventDefault();

        const tripId =
            document.getElementById("participantTripId").value;

        const name =
            document.getElementById("participantName").value;


        fetch("/api/participants", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({

                name: name,

                trip: {
                    id: Number(tripId)
                }

            })

        })

        .then(response => response.json())

        .then(data => {

            document.getElementById("participantMessage")
                .innerText =
                "Participant added successfully! Participant ID: "
                + data.id;

            document.getElementById("participantForm")
                .reset();

        })

        .catch(error => {

            document.getElementById("participantMessage")
                .innerText =
                "Error adding participant";

            console.error(error);

        });

    });

	
	function loadParticipants(tripId) {

	    fetch(`/api/participants/trip/${tripId}`)
	        .then(response => response.json())
	        .then(participants => {

	            const participantList =
	                document.getElementById("participantList");

	            participantList.innerHTML = "";

	            if (participants.length === 0) {

	                participantList.innerHTML =
	                    "<p>No participants found.</p>";

	                return;
	            }

	            participants.forEach(participant => {

	                const participantDiv =
	                    document.createElement("div");

	                participantDiv.className = "participant-card";

	                participantDiv.innerHTML = `
	                    <h3>${participant.name}</h3>

	                    <p>
	                        <strong>Participant ID:</strong>
	                        ${participant.id}
	                    </p>

	                    <div class="participant-actions">

	                        <button
	                            class="edit-btn"
	                            onclick="editParticipant(${participant.id})">
	                            Edit
	                        </button>

	                        <button
	                            class="delete-btn"
	                            onclick="deleteParticipant(${participant.id})">
	                            Delete
	                        </button>

	                    </div>
	                `;

	                participantList.appendChild(participantDiv);
	            });
	        })
	        .catch(error => {

	            console.error(
	                "Error loading participants:",
	                error
	            );

	        });
	}

	
	function editParticipant(id) {

	    const newName = prompt("Enter new participant name:");

	    if (newName === null || newName.trim() === "") {
	        return;
	    }

	    fetch(`/api/participants/${id}`, {
	        method: "PUT",
	        headers: {
	            "Content-Type": "application/json"
	        },
	        body: JSON.stringify({
	            name: newName
	        })
	    })
	    .then(response => response.json())
	    .then(data => {

	        alert("Participant updated successfully!");

	        const tripId =
	            document.getElementById("participantTripId").value;

	        loadParticipants(tripId);

	    })
	    .catch(error => {

	        console.error(
	            "Error updating participant:",
	            error
	        );

	    });
	}


	function deleteParticipant(id) {

	    const confirmDelete =
	        confirm("Are you sure you want to delete this participant?");

	    if (!confirmDelete) {
	        return;
	    }

	    fetch(`/api/participants/${id}`, {
	        method: "DELETE"
	    })
	    .then(response => response.text())
	    .then(message => {

	        alert(message);

	        const tripId =
	            document.getElementById("participantTripId").value;

	        loadParticipants(tripId);

	    })
	    .catch(error => {

	        console.error(
	            "Error deleting participant:",
	            error
	        );

	    });
	}
// ===============================
// ADD EXPENSE
// ===============================

document.getElementById("expenseForm")
    .addEventListener("submit", function(event) {

        event.preventDefault();

        const tripId =
            document.getElementById("expenseTripId").value;

        const description =
            document.getElementById("expenseDescription").value;

        const amount =
            document.getElementById("expenseAmount").value;

        const paidBy =
            document.getElementById("expensePaidBy").value;


        fetch("/api/expenses", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({

                description: description,

                amount: Number(amount),

                trip: {
                    id: Number(tripId)
                },

                paidBy: {
                    id: Number(paidBy)
                }

            })

        })

        .then(response => response.json())

        .then(data => {

            document.getElementById("expenseMessage")
                .innerText =
                "Expense added successfully! Expense ID: "
                + data.id;

            document.getElementById("expenseForm")
                .reset();

        })

        .catch(error => {

            document.getElementById("expenseMessage")
                .innerText =
                "Error adding expense";

            console.error(error);

        });

    });

	
	function loadExpenses(tripId) {

	    fetch(`/api/expenses/trip/${tripId}`)
	        .then(response => response.json())
	        .then(expenses => {

	            const expenseList =
	                document.getElementById("expenseHistory");

	            expenseList.innerHTML = "";

	            if (expenses.length === 0) {

	                expenseList.innerHTML =
	                    "<p>No expenses found.</p>";

	                return;
	            }

	            expenses.forEach(expense => {

	                const expenseDiv =
	                    document.createElement("div");

	                expenseDiv.className = "expense-card";

	                expenseDiv.innerHTML = `
	                    <h3>${expense.description}</h3>

	                    <p>
	                        <strong>Amount:</strong>
	                        ₹${expense.amount}
	                    </p>

	                    <p>
	                        <strong>Expense ID:</strong>
	                        ${expense.id}
	                    </p>

	                    <p>
	                        <strong>Paid By:</strong>
	                        ${expense.paidBy.name}
	                    </p>

	                    <div class="expense-actions">

	                        <button
	                            class="edit-btn"
	                            onclick="editExpense(${expense.id})">
	                            Edit
	                        </button>

	                        <button
	                            class="delete-btn"
	                            onclick="deleteExpense(${expense.id})">
	                            Delete
	                        </button>

	                    </div>
	                `;

	                expenseList.appendChild(expenseDiv);
	            });
	        })
	        .catch(error => {

	            console.error(
	                "Error loading expenses:",
	                error
	            );

	        });
	}
	
	function editExpense(id) {

	    const newDescription =
	        prompt("Enter new expense description:");

	    if (newDescription === null ||
	        newDescription.trim() === "") {
	        return;
	    }

	    const newAmount =
	        prompt("Enter new expense amount:");

	    if (newAmount === null ||
	        newAmount.trim() === "") {
	        return;
	    }

	    const newPaidBy =
	        prompt("Enter participant ID who paid:");

	    if (newPaidBy === null ||
	        newPaidBy.trim() === "") {
	        return;
	    }

	    fetch(`/api/expenses/${id}`, {
	        method: "PUT",
	        headers: {
	            "Content-Type": "application/json"
	        },
	        body: JSON.stringify({
	            description: newDescription,
	            amount: Number(newAmount),
	            paidBy: {
	                id: Number(newPaidBy)
	            }
	        })
	    })
	    .then(response => response.json())
	    .then(data => {

	        alert("Expense updated successfully!");

	        const tripId =
	            document.getElementById("expenseTripId").value;

	        loadExpenses(tripId);

	    })
	    .catch(error => {

	        console.error(
	            "Error updating expense:",
	            error
	        );

	    });
	}


	function deleteExpense(id) {

	    const confirmDelete =
	        confirm("Are you sure you want to delete this expense?");

	    if (!confirmDelete) {
	        return;
	    }

	    fetch(`/api/expenses/${id}`, {
	        method: "DELETE"
	    })
	    .then(response => response.text())
	    .then(message => {

	        alert(message);

	        const tripId =
	            document.getElementById("expenseTripId").value;

	        loadExpenses(tripId);

	    })
	    .catch(error => {

	        console.error(
	            "Error deleting expense:",
	            error
	        );

	    });
	}

// ===============================
// LOAD TRIPS WHEN PAGE OPENS
// ===============================

loadTrips()


// ===============================
// LOAD DASHBOARD STATISTICS
// ===============================

function loadDashboardStats(tripId) {

    if (!tripId) {
        document.getElementById("totalParticipants").innerText = "0";
        document.getElementById("totalExpenses").innerText = "₹0";
        document.getElementById("totalSettlements").innerText = "0";
        return;
    }


    // Total Trips
    fetch("/api/trips")
        .then(response => response.json())
        .then(trips => {

            document.getElementById("totalTrips").innerText =
                trips.length;

        });


    // Total Participants
    fetch("/api/participants/trip/" + tripId)
        .then(response => response.json())
        .then(participants => {

            document.getElementById("totalParticipants").innerText =
                participants.length;

        });


    // Total Expenses
    fetch("/api/expenses/trip/" + tripId)
        .then(response => response.json())
        .then(expenses => {

            let total = 0;

            expenses.forEach(expense => {
                total += expense.amount;
            });

            document.getElementById("totalExpenses").innerText =
                "₹" + total;

        });


    // Total Settlements
    fetch("/api/settlements/trip/" + tripId)
        .then(response => response.json())
        .then(settlements => {

            document.getElementById("totalSettlements").innerText =
                settlements.length;

        });

}
// ===============================
// LOAD BALANCES
// ===============================

function loadBalances(tripId) {

    const balanceList =
        document.getElementById("balanceList");

    balanceList.innerHTML = "";

    if (!tripId) {
        balanceList.innerHTML =
            "<p>Please select a trip.</p>";
        return;
    }

    fetch("/api/balances/trip/" + tripId)

        .then(response => response.json())

        .then(balances => {

            balances.forEach(balance => {

                const balanceDiv =
                    document.createElement("div");

                balanceDiv.className =
                    "balance-card";

                let status = "";

                if (balance.netBalance > 0) {
                    status = "Should Receive";
                }
                else if (balance.netBalance < 0) {
                    status = "Should Pay";
                }
                else {
                    status = "Settled";
                }

                balanceDiv.innerHTML = `
                    <h3>${balance.participantName}</h3>

                    <p>
                        <strong>Paid:</strong>
                        ₹${balance.totalPaid}
                    </p>

                    <p>
                        <strong>Owed:</strong>
                        ₹${balance.totalOwed}
                    </p>

                    <p>
                        <strong>Net Balance:</strong>
                        ₹${balance.netBalance}
                    </p>

                    <p>
                        <strong>Status:</strong>
                        ${status}
                    </p>
                `;

                balanceList.appendChild(balanceDiv);

            });

        })

        .catch(error => {

            console.error(
                "Error loading balances:",
                error
            );

        });
}

// ===============================
// LOAD SETTLEMENTS
// ===============================

function loadSettlements(tripId) {

    const settlementList =
        document.getElementById("settlementList");

    settlementList.innerHTML = "";

    if (!tripId) {
        settlementList.innerHTML =
            "<p>Please select a trip.</p>";
        return;
    }

    fetch("/api/settlements/trip/" + tripId)

        .then(response => response.json())

        .then(settlements => {

            settlements.forEach(settlement => {

                const settlementDiv =
                    document.createElement("div");

                settlementDiv.className =
                    "settlement-card";

                settlementDiv.innerHTML = `
                    <h3>
                        ${settlement.fromParticipant}
                        →
                        ${settlement.toParticipant}
                    </h3>

                    <p>
                        ₹${settlement.amount}
                    </p>
                `;

                settlementList.appendChild(
                    settlementDiv
                );

            });

        })

        .catch(error => {

            console.error(
                "Error loading settlements:",
                error
            );

        });
}


// ===============================
// LOAD BALANCES + SETTLEMENTS
// ===============================

loadBalances();

loadSettlements();

// ===============================
// LOAD TRIPS INTO DROPDOWN
// ===============================

function loadTripDropdown() {

    fetch("/api/trips")

        .then(response => response.json())

        .then(trips => {

            const selectedTrip =
                document.getElementById("selectedTrip");

            selectedTrip.innerHTML =
                '<option value="">Select a trip</option>';

            trips.forEach(trip => {

                const option =
                    document.createElement("option");

                option.value = trip.id;

                option.textContent =
                    trip.name + " (ID: " + trip.id + ")";

                selectedTrip.appendChild(option);

            });

        })

        .catch(error => {

            console.error(
                "Error loading trip dropdown:",
                error
            );

        });
}


// Load trips into dropdown
loadTripDropdown();


// ===============================
// CHANGE SELECTED TRIP
// ===============================

document.getElementById("selectedTrip")
    .addEventListener("change", function() {

        const tripId = this.value;

        loadDashboardStats(tripId);

        loadBalances(tripId);

        loadSettlements(tripId);
		
		loadExpenseHistory(tripId);
		
		loadParticipants(tripId);
		loadExpenses(tripId);

    });
	
	// ===============================
	// ADD TRIP BUTTON
	// ===============================

	document.querySelector(".add-trip-btn")
	    .addEventListener("click", function() {

	        document.querySelector(".add-trip-section")
	            .scrollIntoView({
	                behavior: "smooth"
	            });

	    });
		
		// ===============================
		// LOAD EXPENSE HISTORY
		// ===============================

		function loadExpenseHistory(tripId) {

		    const expenseHistory =
		        document.getElementById("expenseHistory");

		    expenseHistory.innerHTML = "";

		    if (!tripId) {
		        expenseHistory.innerHTML =
		            "<p>Please select a trip.</p>";
		        return;
		    }

		    fetch("/api/expenses/trip/" + tripId)

		        .then(response => response.json())

		        .then(expenses => {

		            if (expenses.length === 0) {

		                expenseHistory.innerHTML =
		                    "<p>No expenses found for this trip.</p>";

		                return;
		            }

		            let table = `
		                <table class="expense-table">

		                    <thead>
		                        <tr>
		                            <th>ID</th>
		                            <th>Description</th>
		                            <th>Amount</th>
		                            <th>Paid By</th>
		                        </tr>
		                    </thead>

		                    <tbody>
		            `;

		            expenses.forEach(expense => {

		                table += `
		                    <tr>

		                        <td>${expense.id}</td>

		                        <td>${expense.description}</td>

		                        <td>₹${expense.amount}</td>

		                        <td>${expense.paidBy.name}</td>

		                    </tr>
		                `;

		            });

		            table += `
		                    </tbody>
		                </table>
		            `;

		            expenseHistory.innerHTML = table;

		        })

		        .catch(error => {

		            console.error(
		                "Error loading expense history:",
		                error
		            );

		            expenseHistory.innerHTML =
		                "<p>Error loading expenses.</p>";

		        });
		}
		
		document.querySelectorAll(".sidebar-link").forEach(link => {

		    link.addEventListener("click", function(event) {

		        event.preventDefault();

		        const page = this.getAttribute("data-page");

		        document.querySelectorAll(".sidebar-link")
		            .forEach(item => {
		                item.classList.remove("active");
		            });

		        this.classList.add("active");

		        if (page === "dashboard") {
		            window.scrollTo({
		                top: 0,
		                behavior: "smooth"
		            });
		        }

		        if (page === "trips") {
		            document.getElementById("trips").scrollIntoView({
		                behavior: "smooth"
		            });
		        }

		        if (page === "participants") {
		            document.getElementById("participants").scrollIntoView({
		                behavior: "smooth"
		            });
		        }

		        if (page === "expenses") {
		            document.getElementById("expenses").scrollIntoView({
		                behavior: "smooth"
		            });
		        }

		        if (page === "settlements") {
		            document.getElementById("settlements").scrollIntoView({
		                behavior: "smooth"
		            });
		        }

		    });

		});