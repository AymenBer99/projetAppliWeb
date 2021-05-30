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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
          <a class="nav-link" href="#"> Acceuil<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="appli/stats.html">Statistiques</a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="MesuresGouv">Mesures Gouvernementales</a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="Vaccination">Vaccination</a>
        </li>
        <li class="nav-item active">
          ${connected}
        </li>
      </ul>
    </div>
  </nav>

  <div class="head margin padding-bottom">

    <div class="row margin">
      <div class="col-sm-12 col-md-6">
        <img src="images/vaccine.jpg" alt="image de vaccins" class="images">
      </div>

      <div class="col-sm-12 col-md-6 d-flex flex-column justify-content-center align-items-center">
        <h1 class="title display-2 align-items-center text-center">COVID</h1>
        <h2 class="subtitle display-4 text-center">Outil permettant de suivre l'Ã©volution de l'Ã©pidÃ©mie</h2>
      </div>

    </div>

  </div>

  <div class="body text-white padding-bottom padding-top">

    <div id="news" class="container padding-bottom">

      <h1 class="center display-2"><i class="fas fa-newspaper"></i></h1>
      <h2 class="center display-3 title">ActualitÃ©s</h2>

      <div id="carouselExample" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
              <h3 class="center">Mesures Jour J</h3>
             <%String[] mesures1 = (String[])request.getAttribute("mesure1"); %>
       <% if (mesures1 != null) { %>
       <% for (int i=0; i<mesures1.length; i++) { %>
         <td><p class = "center"><font size="4" > <%out.println((mesures1[i].split(";"))[0]);%> :</font> <font size="6" ><%out.println((mesures1[i].split(";"))[1]);%></font></p></td>
         <div style="width:400px;height:200px;border:2px solid #000;" class = "center"><%out.println((mesures1[i].split(";"))[2]);%></div>
       <%}} %>
          </div>
          <div class="carousel-item">
              <h3 class="center">Mesures Jour J-1</h3>
              <% String[] mesures2 = (String[])request.getAttribute("mesure2"); %>
       <% if (mesures2 != null) { %>
       <% for (int i=0; i<mesures2.length; i++) { %>
         <td><p class = "center"><font size="4" > <%out.println((mesures2[i].split(";"))[0]);%> :</font> <font size="6" ><%out.println((mesures2[i].split(";"))[1]);%></font></p></td>
         <div style="width:400px;height:200px;border:2px solid #000;" class = "center"><%out.println((mesures2[i].split(";"))[2]);%></div>
       <%}} %>
          </div>
          <div class="carousel-item">
              <h3 class="center">Mesures Jour J-2</h3>
             <% String[] mesures3 = (String[])request.getAttribute("mesure3"); %>
       <% if (mesures3 != null) { %>
       <% for (int i=0; i<mesures3.length; i++) { %>
         <td><p class = "center"><font size="4" > <%out.println((mesures3[i].split(";"))[0]);%> :</font> <font size="6" ><%out.println((mesures3[i].split(";"))[1]);%></font></p></td>
         <div style="width:400px;height:200px;border:2px solid #000;" class = "center"><%out.println((mesures3[i].split(";"))[2]);%></div>
       <%}} %>
        </div>
        <a class="carousel-control-prev left-arrow align-items-left" href="#carouselExample" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next right-arrow" href="#carouselExample" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>

    </div>

    <hr class="padding-bottom">

    <div id="stats" class="container padding-bottom">
      <h2 class="center display-3 title">Statistiques</h2>
      <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
              <h3 class="center">Jour J</h3>
          </div>
          <div class="carousel-item">
              <h3 class="center">Jour J-1</h3>
          </div>
          <div class="carousel-item">
              <h1 class="center">Jour J-2</h1>
          </div>
        </div>
        <a class="carousel-control-prev left-arrow align-items-left" href="#carouselExampleControls" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next right-arrow" href="#carouselExampleControls" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
    </div>

      <hr class="padding-bottom">

  </div>


  <div class="footer padding-top padding-bottom center">
    <div class="container">
      <div class="row">
        <div class="col"><a href=""><button class="btn btn-success" type="button" name="button">Adresse mail de contact</button></a></div>
      </div>
    </div>
  </div>

  <script src=" https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
  <script src="https://kit.fontawesome.com/e77861a799.js" crossorigin="anonymous"></script>
</body>

</html>
