/* =====================
    Configuration
   ===================== */
const API_BASE = 'http://localhost:8080/search/by-code';

/* =====================
    DOM References
   ===================== */
const sourceInput = document.getElementById('sourceCode');
const destInput = document.getElementById('destCode');
const searchBtn = document.getElementById('searchBtn');
const swapBtn = document.getElementById('swapBtn');
const loader = document.getElementById('loader');
const errorBox = document.getElementById('errorBox');
const resultsSection = document.getElementById('resultsSection');

/* =====================
    Swap Stations
   ===================== */
swapBtn.addEventListener('click', () => {
    swapBtn.classList.add('spinning');
    setTimeout(() => swapBtn.classList.remove('spinning'), 400);
    [sourceInput.value, destInput.value] = [destInput.value, sourceInput.value];
});

/* =====================
    Auto Uppercase + Enter Key
   ===================== */
[sourceInput, destInput].forEach(inp => {
    inp.addEventListener('input', () => {
        inp.value = inp.value.toUpperCase();
    });

    inp.addEventListener('keydown', e => {
        if (e.key === 'Enter') searchTrains();
    });
});

/* =====================
    Button Click
   ===================== */
searchBtn.addEventListener('click', searchTrains);

/* =====================
    Calculate Duration
   ===================== */
function calcDuration(dep, arr) {
    const [dh, dm] = dep.split(':').map(Number);
    const [ah, am] = arr.split(':').map(Number);

    let mins = (ah * 60 + am) - (dh * 60 + dm);
    if (mins < 0) mins += 24 * 60;

    const h = Math.floor(mins / 60);
    const m = mins % 60;

    return `${h}h ${m.toString().padStart(2, '0')}m`;
}

/* =====================
    Show Error
   ===================== */
function showError(msg) {
    errorBox.innerHTML = '⚠ ' + msg;
    errorBox.classList.add('active');
}

/* =====================
    Main Search Function
   ===================== */
async function searchTrains() {
    const src = sourceInput.value.trim();
    const dst = destInput.value.trim();

    // Reset UI
    errorBox.classList.remove('active');
    resultsSection.innerHTML = '';

    // Validation
    if (!src || !dst) {
        showError('Please enter both station codes.');
        return;
    }

    if (src === dst) {
        showError('Source and destination cannot be same.');
        return;
    }

    // Loader
    loader.classList.add('active');
    searchBtn.disabled = true;

    try {
        const url = `${API_BASE}?sourceCode=${encodeURIComponent(src)}&destinationCode=${encodeURIComponent(dst)}`;

        const res = await fetch(url);

        if (!res.ok) {
            throw new Error(`HTTP ${res.status}`);
        }

        const data = await res.json();
        renderResults(data);

    } catch (err) {
        showError(
            `Cannot connect to backend (check server).
            <br><small>${err.message}</small>`
        );
    } finally {
        loader.classList.remove('active');
        searchBtn.disabled = false;
    }
}

/* =====================
    Render Results
   ===================== */
function renderResults(trains) {

    if (!trains || trains.length === 0) {
        resultsSection.innerHTML = `
        <div class="no-results">
            🚉 No trains found
        </div>`;
        return;
    }

    let html = `<div class="results-header">
        <strong>${trains.length}</strong> train(s) found
    </div>`;

    trains.forEach((t, i) => {
        html += renderTrainCard(t, i);
    });

    resultsSection.innerHTML = html;
}

/* =====================
    Train Card UI
   ===================== */
function renderTrainCard(t, index) {

    const duration = calcDuration(t.departureTime, t.arrivalTime);

    return `
    <div class="train-card" style="animation-delay:${index * 0.05}s">

        <div class="top">
            <div>
                <strong>${t.train.trainName}</strong>
                <div>#${t.train.trainNumber}</div>
            </div>
            <div>⏱ ${duration}</div>
        </div>

        <div class="middle">

            <div>
                <div>${t.departureTime}</div>
                <div>${t.source.stationCode}</div>
            </div>

            <div>➡</div>

            <div>
                <div>${t.arrivalTime}</div>
                <div>${t.destination.stationCode}</div>
            </div>

        </div>
    </div>
    `;
}