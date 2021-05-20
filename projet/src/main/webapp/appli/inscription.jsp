<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr" dir="ltr">

<head>
  <meta charset="utf-8">
  <title>COVID</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@600&family=Montserrat:wght@200&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/styles.css">
  <link rel="icon" href="images/logo.jpg">
  <script src=" https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
  <script src="https://kit.fontawesome.com/e77861a799.js" crossorigin="anonymous"></script>
</head>

<body class="head">
  <nav class="navbar navbar-expand-lg navbar-dark head">
    <a class="navbar-brand" href=""> <i class="fas fa-laptop-code"></i> COVID</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item active">
          <a class="nav-link" href="../index.html"> Acceuil<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="stats.html">Statistiques</a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="gouvern.html">Mesures Gouvernementales</a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="accination.html">Vaccination</a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="signIn.html">Connexion/Inscription</a>
        </li>
      </ul>
    </div>
  </nav>



  <div class="body text-white padding-bottom padding-top">

    <hr class="padding-bottom" >
    <div class="Inscription">
      <h2 class="center display-3 title">Inscription</h2>
      <form class="center" action="Inscription" method="post">
        <table class="center padding-bottom margin-bottom ">
          <tr>
            <td><p class="text-left ">Nom de famille*</p></td>
            <td><input  class = "center" id="nom" type="text" name="nom" required placeholder="Nom"></td>
            <td><span class="erreur">${form.erreurs['nom']}</span></td>
          </tr>
          <tr>
            <td><p class="text-left ">Prénom</p></td>
            <td><input  class = "center" id="prenom" type="text" name="prenom" required placeholder="Prénom"></td>
            <td><span class="erreur">${form.erreurs['prenom']}</span></td>
          </tr>
          <tr>
            <td><p class="text-left ">Date de naissance*</p></td>
            <td><input  class = "center" id="datedenaissance" type="date" name="datedenaissance" required placeholder="JJ/MM/AAAA"></td>
            <td><span class="erreur">${form.erreurs['datedenaissance']}</span></td>
          </tr>
          <tr>
            <td><p class="text-left ">Adresse email*</p></td>
            <td><input  class = "center" id="email" type="email" name="email" required placeholder="email@domain.com"></td>
          	<td><span class="erreur">${form.erreurs['email']}</span></td>
          </tr>
          <tr>
            <td><p class="text-left ">Mot de passe*</p></td>
            <td><input  class = "center" id="mdp" type="password" name="mdp" required placeholder="Mot de passe"></td>
          	<td><span class="erreur">${form.erreurs['mdp']}</span></td>
          </tr>
          <tr>
            <td><p class="text-left ">Confirmer Mot de passe*</p></td>
            <td><input  class = "center" id="mdp1" type="password" name="mdp1" required placeholder="Mot de passe"></td>
          	<td><span class="erreur">${form.erreurs['mdp1']}</span></td>
          </tr>
        </table>
        <button class="btn btn-success margin-bottom" type="submit" name="submit" id="submit">S'inscrire</button>
      </form>
      <td><p class="text-center">* champs obligatoires</td>
      
      <p class="${empty erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
      
    </div>
      <hr class="padding-bottom">
  </div>




</body>

</html>
