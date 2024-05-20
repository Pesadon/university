<!DOCTYPE html>
<html lang="en">
<?php
    $json = file_get_contents('cards.json');
    $cards = json_decode($json, true);

    $typeColors = array(
        'fire' => '#d6612f',
        'water' => '#5b8097',
        'electric' => '#e3e379',
        'grass' => '#7fc27f',
        'normal' => '#b3b391',
        'bug' => '#b89045',
        'poison' => '#784078'
    );
    ?>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IKémon | <?php echo $cards[$_GET['id']]['name']; ?></title>
    <link rel="stylesheet" href="styles/main.css">
    <link rel="stylesheet" href="styles/details.css">
</head>

<body>
    
    <header>
        <h1><a href="index.php">IKémon</a> > <?php echo $cards[$_GET['id']]['name']; ?></h1>
    </header>
    <div id="content">
        <div id="details" style="border: 5px solid <?php echo $typeColors[strtolower($cards[$_GET['id']]['type'])]; ?>; border-radius: 1em;">
            <div class="image clr-<?php echo $cards[$_GET['id']]['type']; ?>">
                <img src="<?php echo $cards[$_GET['id']]['image']; ?>" alt="">
            </div>
            <div class="info">
                <div class="description">
                    <?php echo $cards[$_GET['id']]['description']; ?>
                </div>
                <span class="card-type"><span class="icon">🏷</span> Type: <?php echo $cards[$_GET['id']]['type']; ?></span>
                <div class="attributes">
                    <div class="card-hp"><span class="icon">❤</span> Health: <?php echo $cards[$_GET['id']]['hp']; ?></div>
                    <div class="card-attack"><span class="icon">⚔</span> Attack: <?php echo $cards[$_GET['id']]['attack']; ?></div>
                    <div class="card-defense"><span class="icon">🛡</span> Defense: <?php echo $cards[$_GET['id']]['defense']; ?></div>
                </div>
            </div>
        </div>
    </div>
    <footer>
        <p>IKémon | ELTE IK Webprogramozás</p>
    </footer>
</body>
</html>
