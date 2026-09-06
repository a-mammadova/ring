const API_URL = "http://localhost:8080/api/reminders";

const map = L.map("map").setView([40.4093, 49.8671], 12 )

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);

let selectedMarker = null;

const reminderMarkers = new Map();

map.on("click", function(event) {
    const latitude = event.latlng.lat;
    const longitude = event.latlng.lng;

    document.getElementById("latitude").value = latitude.toFixed(6);
    document.getElementById("longitude").value = longitude.toFixed(6);

    if (selectedMarker !== null) {map.removeLayer(selectedMarker);}

    selectedMarker = L.marker([latitude, longitude]).addTo(map);

    selectedMarker.bindPopup(`selected location<br> ${latitude.toFixed(6)}, ${longitude.toFixed(6)}`).openPopup();
});

const form = document.getElementById("reminder-form");

form.addEventListener("submit", async function (event) {
    event.preventDefault();

    const title = document.getElementById("title").value.trim();

    const latitude = parseFloat(document.getElementById("latitude").value);
    const longitude = parseFloat(document.getElementById("longitude").value);
    const radius = parseInt(document.getElementById("radius").value);

    if (isNaN(latitude) || isNaN(longitude)) {showStatus("please select a location on the map", false); return;}

    const reminder = { title: title, latitude: latitude, longitude: longitude, radius: radius};

    try {
        showStatus("creating reminder...", true);

        const response = await fetch(API_URL, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(reminder)
        });

        if (!response.ok) {
            const errorText = await response.text();

            throw new Error(errorText || "failed to create reminder");
        }

        const createdReminder = await response.json();

        console.log("created reminder:", createdReminder);

        showStatus("created successfully", true);

        document.getElementById("title").value = "";
        document.getElementById("radius").value = 100;
        document.getElementById("latitude").value = "";
        document.getElementById("longitude").value = "";

        if (selectedMarker !== null) {map.removeLayer(selectedMarker); selectedMarker = null;}

        await loadReminders();
    } catch(error) {
        console.error(error);
        showStatus("error: " + error.message, false);
    }
});

async function loadReminders() {
    try {
        const response = await fetch(API_URL);

        if (!response.ok) {throw new Error("could not  load reminders");}

        const reminders = await response.json();

        displayReminders(reminders);
        displayReminderMarkers(reminders);
    } catch(error) {
        console.error(error);
        document.getElementById("reminders-list").innerHTML = `<p class="empty"> could not connect to backend </p>`;
    }
}

function displayReminders(reminders) {
    const container = document.getElementById("reminders-list");

    if (reminders.length === 0) {container.innerHTML = `<p class="empty"> no reminders yet </p>`; return;}
    container.innerHTML = "";

    reminders.forEach(reminder => {
        const element = document.createElement("div");

        element.className = "reminder";

        element.innerHTML = `
            <div class="reminder-info">
                <div class="reminder-title"> <h2> ${escapeHtml(reminder.title)}</h2> </div>

                <div class="reminder-coords">
                    ${reminder.latitude.toFixed(6)}, ${reminder.longitude.toFixed(6)}

                    <br> radius: ${reminder.radius} m
                </div>
            </div>

            <button class="delete-button" onclick="deleteReminder(${reminder.id})"> delete </button>
        `;

        container.appendChild(element);
    });
}

function displayReminderMarkers(reminders) {
    reminderMarkers.forEach(marker => {map.removeLayer(marker); });

    reminderMarkers.clear();

    reminders.forEach(reminder => {
        const marker = L.marker([reminder.latitude, reminder.longitude]).addTo(map);

        marker.bindPopup(`
            <strong>${escapeHtml(reminder.title)} </strong> <br>
            radius: ${reminder.radius} m <br>
            coordinates: ${reminder.latitude.toFixed(6)}, ${reminder.longitude.toFixed(6)}
        `);

        reminderMarkers.set(reminder.id, marker);
    });
}

async function deleteReminder(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "DELETE"
        });

        if (!response.ok) {throw new Error("failed to delete reminder");}

        await loadReminders();

        showStatus("reminder deleted", true);
    } catch(error) {
        console.error(error);
        showStatus("error:" + error.message, false);
    }
}

function showStatus(message, success) {
    const status = document.getElementById("status");

    status.textContent = message;

    status.className = success ? "status-success" : "status-error";
}

function escapeHtml(value) {
    const div = document.createElement("div");

    div.textContent = value;

    return div.innerHTML;
}

loadReminders();