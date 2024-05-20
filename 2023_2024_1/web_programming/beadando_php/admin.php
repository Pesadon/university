<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IKémon Admin</title>
    <link rel="stylesheet" href="styles/main.css">
    <link rel="stylesheet" href="styles/admin.css">
</head>

<body>
    <header>
        <h1>IKémon Admin - Create a New Card</h1>
    </header>
    <div id="content">
        <form method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="type">Type:</label>
            <select id="type" name="type" required>
                <?php
                // Read existing types from cards.json
                $cards = json_decode(file_get_contents('cards.json'), true);
                $existingTypes = array_unique(array_column($cards, 'type'));

                // Generate dropdown options
                foreach ($existingTypes as $type) {
                    echo "<option value=\"$type\">$type</option>";
                }
                ?>
            </select>

            <label for="hp">Health Points (HP):</label>
            <input type="number" id="hp" name="hp" required>

            <label for="attack">Attack:</label>
            <input type="number" id="attack" name="attack" required>

            <label for="defense">Defense:</label>
            <input type="number" id="defense" name="defense" required>

            <label for="price">Price:</label>
            <input type="number" id="price" name="price" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>

            <label for="image">Image URL:</label>
            <input type="text" id="image" name="image" required>

            <input type="submit" value="Create Card">
        </form>
    </div>
    <footer>
        <p>IKémon | ELTE IK Webprogramozás</p>
    </footer>
</body>

<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Validate form inputs
    $name = $_POST["name"];
    $type = $_POST["type"];
    $hp = intval($_POST["hp"]);
    $attack = intval($_POST["attack"]);
    $defense = intval($_POST["defense"]);
    $price = intval($_POST["price"]);
    $description = $_POST["description"];
    $image = $_POST["image"];

    $errors = [];

    // Basic validation for required fields
    if (empty($name)) {
        $errors[] = "Name is required.";
    }

    if (empty($type)) {
        $errors[] = "Type is required.";
    }

    if (empty($description)) {
        $errors[] = "Description is required.";
    }

    // Additional validation based on your specific requirements
    // For example, you can check if numeric fields are non-negative, etc.

    if (!empty($errors)) {
        // Display errors and redirect back to the admin page
        echo "<h2>Validation Errors:</h2>";
        foreach ($errors as $error) {
            echo "<p>$error</p>";
        }
        echo "<a href='admin.php'>Go back to admin page</a>";
        exit();
    }

    // If validation passes, proceed to update the cards.json file
    $cards = json_decode(file_get_contents('cards.json'), true);
    $newCardId = 'card' . count($cards);

    $newCard = array(
        "name" => $name,
        "type" => $type,
        "hp" => $hp,
        "attack" => $attack,
        "defense" => $defense,
        "price" => $price,
        "description" => $description,
        "image" => $image
    );

    $cards[$newCardId] = $newCard;

    file_put_contents('cards.json', json_encode($cards, JSON_PRETTY_PRINT));

    // Redirect back to admin page
    header("Location: admin.php");
    exit();
}
?>

</html>