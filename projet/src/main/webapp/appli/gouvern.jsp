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
          <a class="nav-link" href="/projet/Acceuil"> Acceuil<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="/projet/Statistiques">Statistiques</a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="/projet/MesuresGouv">Mesures Gouvernementales</a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="/projet/Vaccination">Vaccination</a>
        </li>
         <li class="nav-item active">
          ${connected}
        </li>
      </ul>
    </div>
  </nav>



  <div class="body text-white padding-bottom padding-top">

    <hr class="padding-bottom">
      <div class="MesuresGouv">
        <h2 class="center display-3 title">Mesures Gouvernemantales</h2>
        <h3 class="center display-6 subtitle"> Découvrez les dernires annonces concernant l'évolution de l'épidemie en France</h3>
      </div>
      <form class = "center" action = "MesuresGouv" method= "post">
      <table class="center padding-bottom margin-bottom ">
        <tr>
          <td><p class="text-left ">Date des annonces</p></td>
          <td><input  class = "center" id="dateannonce" type="date" name="dateannonce" required placeholder="JJ/MM/AAAA"></td>
        </tr>
        </table>
        <button class="btn btn-success margin-bottom" type="submit" name="submit" id="submit">Chercher</button>
        ${erreur}
        </form>      
           ${ajoutmesure}
      <hr class="padding-bottom">
      
   </div>
  <div class="body text-white padding-bottom padding-top">     
       <% String[] mesures = (String[])request.getAttribute("mesure"); %>
       <% if (mesures != null) { %>
       <% for (int i=0; i<mesures.length; i++) { %>
         <td><p class = "center"><font size="4" > <%out.println((mesures[i].split(";"))[0]);%> :</font> <font size="6" ><%out.println((mesures[i].split(";"))[1]);%></font></p></td>
         <div style="overflow-wrap: break-word;width:1500px;border:2px solid #000;" class = "center"><span><%out.println((mesures[i].split(";"))[2]);%></span></div>
       <%}} %>
  </div>




</body>

</html>