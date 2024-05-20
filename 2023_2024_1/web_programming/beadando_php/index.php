<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IKémon | Home</title>
    <link rel="stylesheet" href="styles/main.css">
    <link rel="stylesheet" href="styles/cards.css">
</head>

<body>
    <header>
        <h1><a href="index.php">IKémon</a> > Home</h1>
    </header>
    <div id="content">
        <div id="card-list">
           <?php
           // Decode JSON data
           $json = file_get_contents('cards.json');
        $cards = json_decode($json, true);

// Loop through cards and generate HTML
foreach ($cards as $cardId => $card) {
    echo '
        <div class="pokemon-card">
            <div class="image clr-' . $card['type'] . '">
                <img src="' . $card['image'] . '" alt="">
            </div>
            <div class="details">
                <h2><a href="details.php?id=' . $cardId . '">' . $card['name'] . '</a></h2>
                <span class="card-type"><span class="icon">🏷</span> ' . $card['type'] . '</span>
                <span class="attributes">
                    <span class="card-hp"><span class="icon">❤</span> ' . $card['hp'] . '</span>
                    <span class="card-attack"><span class="icon">⚔</span> ' . $card['attack'] . '</span>
                    <span class="card-defense"><span class="icon">🛡</span> ' . $card['defense'] . '</span>
                </span>
            </div>
            <div class="buy">
                <span class="card-price"><span class="icon">💰</span> ' . $card['price'] . '</span>
            </div>
        </div>
        </a>';
}
?>
        </div>
    </div>
    <footer>
        <p>IKémon | ELTE IK Webprogramozás</p>
    </footer>
</body>

</html>