let board = [["base", "base", "base", "base", "base", "base", "base", "base", "base", "base", "base"],
["base", "mountain", "base", "base", "base", "base", "base", "base", "base", "base", "base"],
["base", "base", "base", "base", "base", "base", "base", "base", "base", "base", "base"],
["base", "base", "base", "base", "base", "base", "base", "base", "mountain", "base", "base"],
["base", "base", "base", "base", "base", "base", "base", "base", "base", "base", "base"],
["base", "base", "base", "mountain", "base", "base", "base", "base", "base", "base", "base"],
["base", "base", "base", "base", "base", "base", "base", "base", "base", "base", "base"],
["base", "base", "base", "base", "base", "base", "base", "base", "base", "base", "base"],
["base", "base", "base", "base", "base", "base", "base", "base", "base", "mountain", "base"],
["base", "base", "base", "base", "base", "mountain", "base", "base", "base", "base", "base"],
["base", "base", "base", "base", "base", "base", "base", "base", "base", "base", "base"]];

const elements = [
    {
        time: 2,
        type: 'water',
        shape: [[1, 1, 1],
        [0, 0, 0],
        [0, 0, 0]],
        rotation: 0,
        mirrored: false
    },
    {
        time: 2,
        type: 'village',
        shape: [[1, 1, 1],
        [0, 0, 0],
        [0, 0, 0]],
        rotation: 0,
        mirrored: false
    },
    {
        time: 1,
        type: 'forest',
        shape: [[1, 1, 0],
        [0, 1, 1],
        [0, 0, 0]],
        rotation: 0,
        mirrored: false
    },
    {
        time: 2,
        type: 'plains',
        shape: [[1, 1, 1],
        [0, 0, 1],
        [0, 0, 0]],
        rotation: 0,
        mirrored: false
    },
    {
        time: 2,
        type: 'forest',
        shape: [[1, 1, 1],
        [0, 0, 1],
        [0, 0, 0]],
        rotation: 0,
        mirrored: false
    },
    {
        time: 2,
        type: 'village',
        shape: [[1, 1, 1],
        [0, 1, 0],
        [0, 0, 0]],
        rotation: 0,
        mirrored: false
    },
    {
        time: 2,
        type: 'plains',
        shape: [[1, 1, 1],
        [0, 1, 0],
        [0, 0, 0]],
        rotation: 0,
        mirrored: false
    },
    {
        time: 1,
        type: 'village',
        shape: [[1, 1, 0],
        [1, 0, 0],
        [0, 0, 0]],
        rotation: 0,
        mirrored: false
    },
    {
        time: 1,
        type: 'village',
        shape: [[1, 1, 1],
        [1, 1, 0],
        [0, 0, 0]],
        rotation: 0,
        mirrored: false
    },
    {
        time: 1,
        type: 'plains',
        shape: [[1, 1, 0],
        [0, 1, 1],
        [0, 0, 0]],
        rotation: 0,
        mirrored: false
    },
    {
        time: 1,
        type: 'plains',
        shape: [[0, 1, 0],
        [1, 1, 1],
        [0, 1, 0]],
        rotation: 0,
        mirrored: false
    },
    {
        time: 2,
        type: 'water',
        shape: [[1, 1, 1],
        [1, 0, 0],
        [1, 0, 0]],
        rotation: 0,
        mirrored: false
    },
    {
        time: 2,
        type: 'water',
        shape: [[1, 0, 0],
        [1, 1, 1],
        [1, 0, 0]],
        rotation: 0,
        mirrored: false
    },
    {
        time: 2,
        type: 'forest',
        shape: [[1, 1, 0],
        [0, 1, 1],
        [0, 0, 1]],
        rotation: 0,
        mirrored: false
    },
    {
        time: 2,
        type: 'forest',
        shape: [[1, 1, 0],
        [0, 1, 1],
        [0, 0, 0]],
        rotation: 0,
        mirrored: false
    },
    {
        time: 2,
        type: 'water',
        shape: [[1, 1, 0],
        [1, 1, 0],
        [0, 0, 0]],
        rotation: 0,
        mirrored: false
    },
]

let currentElement;

let points = 0;
let season = 0;
let currentMissions = "";
let timeLeftSeason = 7;
let timeLeftGame = 28;

let springPoints = 0;
let summerPoints = 0;
let autumnPoints = 0;
let winterPoints = 0;

let missions = [
    {
        name: "az erdő széle",
        points: 0
    },
    {
        name: "Álmos-völgy",
        points: 0
    },
    {
        name: "krumpliöntözés",
        points: 0
    },
    {
        name: "határvidék",
        points: 0
    },
]

document.addEventListener("DOMContentLoaded", function () {
    const gridContainer = document.getElementById("grid-container");

    for (let i = 0; i < 11; i++) {
        for (let j = 0; j < 11; j++) {
            const cell = document.createElement("div");
            cell.classList.add("grid-cell");
            cell.setAttribute("data-x", i);
            cell.setAttribute("data-y", j);

            cell.setAttribute("data-type", board[i][j]);
            cell.setAttribute("style", "background-image: url('img/" + board[i][j] + "_tile.png')");

            gridContainer.appendChild(cell);
        }
    }

    itemToBePlaced();
});

function itemToBePlaced() {
    const rand = Math.floor(Math.random() * elements.length);
    placeditem.innerHTML = "";

    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            const cell = document.createElement("div");
            cell.classList.add("tbpCell");
            cell.setAttribute("data-x", i);
            cell.setAttribute("data-y", j);

            if (elements[rand].shape[i][j] == 1) {
                cell.setAttribute("style", "background-image: url('img/" + elements[rand].type + "_tile.png')");
            }

            placeditem.appendChild(cell);
        }
    }

    currentElement = elements[rand];

    requiredtime.innerHTML = currentElement.time;


    missionCheck();
    
}

function insertCurrent(event) {
    const clickedCell = event.target;

    const x = parseInt(clickedCell.getAttribute("data-x"));
    const y = parseInt(clickedCell.getAttribute("data-y"));

    const gridCells = document.getElementsByClassName("grid-cell");

    let canPlace = true;

    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            const cell = document.querySelector("[data-x='" + (x + i) + "'][data-y='" + (y + j) + "']");
            if (currentElement.shape[i][j] == 1 && cell == null) {
                canPlace = false;
            }
            else if (currentElement.shape[i][j] == 1 && cell.getAttribute("data-type") != "base") {
                canPlace = false;
            }
        }
    }

    if (canPlace == true) {
        for (let i = 0; i < 3; i++) {
            for (let j = 0; j < 3; j++) {
                if (currentElement.shape[i][j] == 1) {
                    const cell = document.querySelector("[data-x='" + (x + i) + "'][data-y='" + (y + j) + "']");
                    cell.setAttribute("data-type", currentElement.type);
                    cell.setAttribute("style", "background-image: url('img/" + currentElement.type + "_tile.png')");

                    board[x + i][y + j] = currentElement.type;
                }
            }
        }

        
        console.log(timeLeftGame);

        itemToBePlaced();
        currentseason.innerHTML = seasonCheck(currentElement.time);
        endCheck();
    }
    else {
        alert("Érvénytelen elhelyezés!");
    }
}

function seasonCheck(minustime) {
    let seasons = ['Tavasz', 'Nyár', 'Ősz', 'Tél'];
    timeLeftSeason -= minustime;
    timeLeftGame -= minustime;

    if (timeLeftSeason == 0) {
        timeLeftSeason = 7;
        season++;
    }
    else if(timeLeftSeason < 0) {
        timeLeftSeason = 6;
        season++;
    }

    timeleft.innerHTML = timeLeftSeason;
    return seasons[season % 4];
}

function missionCheck() {
    switch (season) {
        case 0:
            spring();
            break;
        case 1:
            summer();
            break;
        case 2:
            autumn();
            break;
        case 3:
            winter();
            break;
    }
}

function spring() {
    missions[0].points = erdoSzele();
    missions[1].points = almosVolgy();

    springPoints = missions[0].points + missions[1].points;
    points = springPoints;

    springpoint.innerHTML = springPoints;
    sum.innerHTML = points;

    currentmission1.innerHTML = missions[0].name + " (" + missions[0].points + " pont)";
    currentmission2.innerHTML = missions[1].name + " (" + missions[1].points + " pont)";

    erdoszelepoint.innerHTML = missions[0].points;
    almosvolgypoint.innerHTML = missions[1].points;

    document.getElementById("erdoszele").setAttribute("style", "border: 5px solid yellow");
    document.getElementById("almosvolgy").setAttribute("style", "border: 5px solid yellow");
}

function summer() {
    missions[1].points = almosVolgy();
    missions[2].points = krumpliontozes();

    summerPoints = missions[1].points + missions[2].points;
    points = springPoints + summerPoints;

    summerpoint.innerHTML = summerPoints;
    sum.innerHTML = points;

    currentmission1.innerHTML = missions[1].name + " (" + missions[1].points + " pont)";
    currentmission2.innerHTML = missions[2].name + " (" + missions[2].points + " pont)";

    almosvolgypoint.innerHTML = missions[1].points;
    krumpliontozespoint.innerHTML = missions[2].points;

    document.getElementById("erdoszele").setAttribute("style", "border: 0px solid yellow");
    document.getElementById("almosvolgy").setAttribute("style", "border: 5px solid yellow");
    document.getElementById("krumpliontozes").setAttribute("style", "border: 5px solid yellow");
}

function autumn() {
    missions[2].points = krumpliontozes();
    missions[3].points = hatarvidek();

    autumnPoints = missions[2].points + missions[3].points;
    points = springPoints + summerPoints + autumnPoints;

    autumnpoint.innerHTML = autumnPoints;
    sum.innerHTML = points;

    currentmission1.innerHTML = missions[2].name + " (" + missions[2].points + " pont)";
    currentmission2.innerHTML = missions[3].name + " (" + missions[3].points + " pont)";

    krumpliontozespoint.innerHTML = missions[2].points;
    hatarvidekpoint.innerHTML = missions[3].points;

    document.getElementById("almosvolgy").setAttribute("style", "border: 0px solid yellow");
    document.getElementById("krumpliontozes").setAttribute("style", "border: 5px solid yellow");
    document.getElementById("hatarvidek").setAttribute("style", "border: 5px solid yellow");
}

function winter() {
    missions[3].points = hatarvidek();
    missions[0].points = erdoSzele();

    winterPoints = missions[3].points + missions[0].points;
    points = springPoints + summerPoints + autumnPoints + winterPoints;

    winterpoint.innerHTML = winterPoints;
    sum.innerHTML = points;

    currentmission1.innerHTML = missions[3].name + " (" + missions[3].points + " pont)";
    currentmission2.innerHTML = missions[0].name + " (" + missions[0].points + " pont)";

    hatarvidekpoint.innerHTML = missions[3].points;
    erdoszelepoint.innerHTML = missions[0].points;

    document.getElementById("krumpliontozes").setAttribute("style", "border: 0px solid yellow");
    document.getElementById("hatarvidek").setAttribute("style", "border: 5px solid yellow");
    document.getElementById("erdoszele").setAttribute("style", "border: 5px solid yellow");
}

function erdoSzele() {
    let points = 0;
    for (let i = 0; i < 11; i++) {
        for (let j = 0; j < 11; j++) {
            if (board[i][j] == "forest") {
                if (i == 0 || i == 10 || j == 0 || j == 10) {
                    points++;
                }
            }
        }
    }

    return points;
}

function almosVolgy() {
    let points = 0;
    for (let i = 0; i < 11; i++) {
        let forestCount = 0;
        for (let j = 0; j < 11; j++) {
            if (board[i][j] == "forest") {
                forestCount++;
            }
        }
        if (forestCount == 3) {
            points+=4;
        }
    }

    return points;
}

function krumpliontozes() {
    let points = 0;
    for (let i = 0; i < 11; i++) {
        for (let j = 0; j < 11; j++) {
            if (board[i][j] == "plains") {
                if (i > 0 && i < 10 && j > 0 && j < 10 && (board[i + 1][j] == "water" || board[i - 1][j] == "water" || board[i][j + 1] == "water" || board[i][j - 1] == "water")) {
                    points += 2;
                }
            }
        }
    }

    return points;
}

function hatarvidek() {
    let points = 0;
    for (let i = 0; i < 11; i++) {
        let fullRow = true;
        for (let j = 0; j < 11; j++) {
            if (board[i][j] == "base") {
                fullRow = false;
            }
        }
        if (fullRow) {
            points += 6;
        }
    }

    for (let i = 0; i < 11; i++) {
        let fullColumn = true;
        for (let j = 0; j < 11; j++) {
            if (board[j][i] == "base") {
                fullColumn = false;
            }
        }
        if (fullColumn) {
            points += 6;
        }
    }

    return points;
}

function surroundMountain() {
    let points = 0;
    for (let i = 0; i < 11; i++) {
        for (let j = 0; j < 11; j++) {
            if (board[i][j] == "mountain") {
                if (board[i - 1][j - 1] != "base" && board[i - 1][j] != "base" && board[i - 1][j + 1] != "base" && board[i][j - 1] != "base" && board[i][j + 1] != "base" && board[i + 1][j - 1] != "base" && board[i + 1][j] != "base" && board[i + 1][j + 1] != "base") {
                    points++;
                }
            }
        }
    }

    return points;
}

function rotateCurrent() {
    let rotated = [];
    for (let i = 0; i < 3; i++) {
        rotated[i] = [];
        for (let j = 0; j < 3; j++) {
            rotated[i][j] = currentElement.shape[2 - j][i];
        }
    }

    currentElement.shape = rotated;

    placeditem.innerHTML = "";

    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            const cell = document.createElement("div");
            cell.classList.add("tbpCell");
            cell.setAttribute("data-x", i);
            cell.setAttribute("data-y", j);

            if (currentElement.shape[i][j] == 1) {
                cell.setAttribute("style", "background-image: url('img/" + currentElement.type + "_tile.png')");
            }

            placeditem.appendChild(cell);
        }
    }
}

function mirrorCurrent() {
    let mirrored = [];
    for (let i = 0; i < 3; i++) {
        mirrored[i] = [];
        for (let j = 0; j < 3; j++) {
            mirrored[i][j] = currentElement.shape[i][2 - j];
        }
    }

    currentElement.shape = mirrored;

    placeditem.innerHTML = "";

    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            const cell = document.createElement("div");
            cell.classList.add("tbpCell");
            cell.setAttribute("data-x", i);
            cell.setAttribute("data-y", j);

            if (currentElement.shape[i][j] == 1) {
                cell.setAttribute("style", "background-image: url('img/" + currentElement.type + "_tile.png')");
            }

            placeditem.appendChild(cell);
        }
    }
}

function skip() {
    currentseason.innerHTML = seasonCheck(currentElement.time);
    missionCheck();
    itemToBePlaced();
    console.log(timeLeftGame);
    endCheck();
}

function endCheck() {
    if (timeLeftGame <= 0) {
        alert("Vége a játéknak! Pontszám: " + (points + surroundMountain()) + "\nTavasz: " + springPoints + "\nNyár: " + summerPoints + "\nŐsz: " + autumnPoints + "\nTél: " + winterPoints + "\n\n" + "Erdő széle: " + missions[0].points + "\nÁlmos-völgy: " + missions[1].points + "\nKrumpliöntözés: " + missions[2].points + "\nHatárvidék: " + missions[3].points + "\n\n" + "Pluszpont hegy körülvétele miatt: " + surroundMountain());

        location.reload();
    }
}