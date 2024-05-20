module PlantsVsZombies where

import Data.List

type Coordinate = (Int, Int)
type Sun = Int

data Plant = Peashooter Int | Sunflower Int | Walnut Int | CherryBomb Int deriving (Eq, Show)

data Zombie = Basic Int Int | Conehead Int Int | Buckethead Int Int | Vaulting Int Int deriving (Eq, Show)

data GameModel = GameModel Sun [(Coordinate, Plant)] [(Coordinate, Zombie)] deriving (Eq, Show)

defaultPeashooter :: Plant
defaultPeashooter = Peashooter 3

defaultSunflower :: Plant
defaultSunflower = Sunflower 2

defaultWalnut :: Plant
defaultWalnut = Walnut 15

defaultCherryBomb :: Plant
defaultCherryBomb = CherryBomb 2

basic :: Zombie
basic = Basic 5 1

coneHead :: Zombie
coneHead = Conehead 10 1

bucketHead :: Zombie
bucketHead = Buckethead 20 1

vaulting :: Zombie
vaulting = Vaulting 7 2


tryPurchase :: GameModel -> Coordinate -> Plant -> Maybe GameModel
tryPurchase (GameModel suns plants zombies) coordinate plant
    | (coordinate `elem` (map fst plants)) = Nothing
    | (fst coordinate <= 4) && (fst coordinate >= 0) && (snd coordinate <= 11) && (snd coordinate >= 0) && (suns >= plantCost plant) = Just (GameModel (suns - plantCost plant) ((coordinate, plant) : plants) zombies)
    | otherwise = Nothing

plantCost :: Plant -> Int
plantCost (Peashooter _) = 100
plantCost (Sunflower _) = 50
plantCost (Walnut _) = 50
plantCost (CherryBomb _) = 150


placeZombieInLane :: GameModel -> Zombie -> Int -> Maybe GameModel
placeZombieInLane (GameModel suns plants zombies) zombie lane
    | (lane <= 4) && (lane >= 0) && (not (any (\(coordinate, _) -> fst coordinate == lane && snd coordinate == 11) zombies)) = Just (GameModel suns plants (((lane, 11), zombie) : zombies))
    | otherwise = Nothing



performZombieActions :: GameModel -> Maybe GameModel
performZombieActions (GameModel suns plants zombies)
    | any (\(coordinate, _) -> snd coordinate == 0) zombies = Nothing
    | otherwise = Just (GameModel suns (plantsList plants (GameModel suns plants zombies)) (zombiesList zombies (GameModel suns plants zombies)))

isThereAPlant :: (Coordinate,Zombie) -> GameModel -> (Bool,Int)
isThereAPlant (coord,zombie) (GameModel suns plants zombies) 
    | zombieSpeed zombie == 2 && any (\(coordinate, _) -> coordinate == coord) plants == True = (True,0) 
    | zombieSpeed zombie == 2 && any (\(coordinate,_) -> fst coordinate == fst coord && snd coordinate == snd coord - 1) plants == True = (True,1)
    | otherwise = ((any (\(coordinate, _) -> coordinate == coord) plants),2)

isThereAZombie :: (Coordinate, Plant) -> GameModel -> (Bool, Int)
isThereAZombie (coord,plant) (GameModel suns plants zombies)
    | any (\(coordinate, _) -> coordinate == coord) zombies = (True, zombieSpeed (snd (head (filter (\(coordinate, _) -> coordinate == coord) zombies))))
    | otherwise = (False, 0)

plantsList :: [(Coordinate, Plant)] -> GameModel -> [(Coordinate, Plant)]
plantsList [] (GameModel suns plants zombies) = []
plantsList ((coord,plant):xs) (GameModel suns plants zombies)
    | isThereAZombie (coord,plant) (GameModel suns plants zombies) == (True, 1) = ((coord, (decreasePlantHealth plant (zombiesOnCoordinate coord (GameModel suns plants zombies)))) : plantsList xs (GameModel suns plants zombies))
    | otherwise = ((coord,plant) : plantsList xs (GameModel suns plants zombies))

zombiesList :: [(Coordinate, Zombie)] -> GameModel -> [(Coordinate, Zombie)]
zombiesList [] (GameModel suns plants zombies) = []
zombiesList ((coord,zombie):xs) (GameModel suns plants zombies)
    | isThereAPlant (coord,zombie) (GameModel suns plants zombies) == (True,0) = (((fst coord,(snd coord)-1), slowZombie zombie):zombiesList xs (GameModel suns plants zombies))
    | isThereAPlant (coord,zombie) (GameModel suns plants zombies) == (True,1) = (((fst coord,(snd coord)-2), slowZombie zombie):zombiesList xs (GameModel suns plants zombies))
    | isThereAPlant (coord,zombie) (GameModel suns plants zombies) == (True,2) = ((coord, slowZombie zombie):zombiesList xs (GameModel suns plants zombies))    
    | otherwise = ((stepAhead zombie coord, zombie) : zombiesList xs (GameModel suns plants zombies))

stepAhead :: Zombie -> Coordinate -> Coordinate
stepAhead zombie coordinate = (fst coordinate, snd coordinate - zombieSpeed zombie)

zombiesOnCoordinate :: Coordinate -> GameModel -> Int
zombiesOnCoordinate coordinate (GameModel suns plants zombies) = length (filter (\(coord, zombie) -> coord == coordinate && zombieSpeed zombie == 1) zombies)

decreasePlantHealth :: Plant -> Int -> Plant
decreasePlantHealth (Peashooter health) x = Peashooter (health - x)
decreasePlantHealth (Sunflower health) x = Sunflower (health - x)
decreasePlantHealth (Walnut health) x = Walnut (health - x)
decreasePlantHealth (CherryBomb health) x = CherryBomb (health - x)

zombieSpeed :: Zombie -> Int
zombieSpeed (Basic _ speed) = speed
zombieSpeed (Conehead _ speed) = speed
zombieSpeed (Buckethead _ speed) = speed
zombieSpeed (Vaulting _ speed) = speed

slowZombie :: Zombie -> Zombie
slowZombie zombie = (zombieType zombie) (zombieHealth zombie) 1

zombieType :: Zombie -> Int -> Int -> Zombie
zombieType (Basic _ _) health speed = Basic health speed
zombieType (Conehead _ _) health speed = Conehead health speed
zombieType (Buckethead _ _) health speed = Buckethead health speed
zombieType (Vaulting _ _) health speed = Vaulting health speed

zombieHealth :: Zombie -> Int
zombieHealth (Basic health _) = health
zombieHealth (Conehead health _) = health
zombieHealth (Buckethead health _) = health
zombieHealth (Vaulting health _) = health

cleanBoard :: GameModel -> GameModel
cleanBoard (GameModel suns plants zombies) = GameModel suns (filter (\(_, plant) -> plantHealth plant > 0) plants) (filter (\(_, zombie) -> zombieHealth zombie > 0) zombies)

plantHealth :: Plant -> Int
plantHealth (Peashooter health) = health
plantHealth (Sunflower health) = health
plantHealth (Walnut health) = health
plantHealth (CherryBomb health) = health