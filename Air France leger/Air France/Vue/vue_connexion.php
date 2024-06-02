<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Air France</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Style pour le logo */
        .logo {
            width: 200px; /* Taille du logo agrandie */
            height: auto;
            margin: 0 auto; /* Centrer horizontalement */
            display: block; /* Pour centrer */
            border-radius: 0; /* Supprimer les coins arrondis */
        }
        /* Style pour le titre */
        h3 {
            text-align: center; /* Centrer le texte */
            font-size: 28px; /* Taille de police agrandie */
            margin-top: 20px; /* Espacement en haut */
            color: #FF0000; /* Couleur du texte rouge */
            font-family: 'Arial Black', sans-serif; /* Police spécifique */
        }
        /* Style pour le fond d'écran */
        body {
            background-color: #ADD8E6; /* Couleur de fond bleu clair */
            color: #fff; /* Couleur du texte blanc */
            padding-top: 20px; /* Espacement en haut pour le logo */
        }
        /* Style pour les champs de formulaire */
        input[type="text"],
        input[type="password"] {
            width: 250px; /* Largeur des champs réduite */
        }
        /* Style pour les étiquettes */
        .label-style {
            color: #fff; /* Couleur du texte en blanc */
            font-weight: bold; /* Texte en gras */
        }
        /* Style pour les boutons */
        .btn-container {
            text-align: center; /* Centrer les boutons */
            margin-top: 20px; /* Espacement en haut des boutons */
        }
        /* Style pour le bouton Se Connecter */
        .btn-primary {
            background-color: #FF0000; /* Fond rouge */
        }
    </style>
</head>
<body>
    <div class="container">
        <img src="images/logo.jpeg" alt="Air France Logo" class="logo">
        <h3>Air France</h3>
        <div class="mt-5">
            <form method="post">
                <div class="form-group"> <!-- Utiliser des classes Bootstrap pour aligner les éléments -->
                    <label class="label-style" for="email">Email</label>
                    <input type="text" name="email" id="email" class="form-control">
                </div>
                <div class="form-group">
                    <label class="label-style" for="mdp">Mot De Passe</label>
                    <input type="password" name="mdp" id="mdp" class="form-control">
                </div>
                <div class="btn-container">
                    <input type="reset" value="Annuler" name="Annuler" class="btn btn-secondary">
                    <input type="submit" value="Se Connecter" name="seConnecter" class="btn btn-primary">
                </div>
            </form>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
