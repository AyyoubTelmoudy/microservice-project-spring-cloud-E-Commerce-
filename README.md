
# Contrôle           
# Matière : JEE 
Nom complet : Ayyoub TELMOUDY
Nom complet : Ayyoub TELMOUDY
Filière : IIR Niveau : 5 
Groupe : G3 / 32

1) customer-service:
 Dans cet etap on crée un microservice qui va gérer les clients et on lui envoie des requêtes http à travers son adresse :
 
![CHEESE!](steps/customers_microservice.PNG)

2) inventory-service:

 Dans cet etap on crée un microservice qui va gérer les produits et on va faire la même chose que l'étape précédent :
 
 ![CHEESE!](steps/products_microservice.PNG)
 
 3) Gateway Spring cloud :
    Gateway avec une Configuration statique du système de routage :
 
 ![CHEESE!](steps/gateway_static_configuration.PNG)

  le microservice customer-service à travers la gateway 

    ![CHEESE!](steps/1.PNG)

  le microservice inverntory-service à travers la gateway

    ![CHEESE!](steps/2.PNG)

 4) l'annuaire Eureka Discrovery Service :

   ![CHEESE!](steps/eruka_server.PNG)

 5) Configuration dynamique des routes de la gateway
 
  ![CHEESE!](steps/dynamic_configuration_gateway.PNG)
  
 6) Facturation Billing-Service en utilisant Open Feign
 
  ![CHEESE!](steps/billing_microservices.PNG)
  
7) client Web Angular (Clients, Produits, Factures)

 Produits :

 ![CHEESE!](steps/products.PNG)
 
  Clients :

 ![CHEESE!](steps/customers.PNG)
 
  Factures :

 ![CHEESE!](steps/orders.PNG)
 
  Details d'une facture :

 ![CHEESE!](steps/order-details.PNG)
 
 8) Déployer le serveur keycloak :
   création d'une realm :
   
 ![CHEESE!](steps/create_realm.PNG)
 
 
    création d'un client :
   
 ![CHEESE!](steps/create_client.PNG)
 
 
    création des utilisateurs :
   
 ![CHEESE!](steps/Users.PNG)
 
 
 création des rôles 
 
 ![CHEESE!](steps/create_roles.PNG)
 
 
  affectation des rôles 
 
 ![CHEESE!](steps/assign_roles.PNG)
 
   Authentification avec username et password  
 
 ![CHEESE!](steps/mode_1.PNG)
 
 
    Authentification avec refresh token  
 
 ![CHEESE!](steps/mode_2.PNG)
 
 
     Authentification avec client_secret
 
 ![CHEESE!](steps/mode_3.PNG)
 
 9) Sécuriser les micro-services on créatn deux classe pour chaque microservice : 
 
 Seucirty configuration
 
 ![CHEESE!](steps/security_configuration.PNG)
 
  
keycloak adapter
 
 ![CHEESE!](steps/keycloak_adapter.PNG)
 
 
frontend angular
installation de la library keycloak-js et keycloak-angular et configuration de keycloak adapter

 ![CHEESE!](steps/keycloak1.PNG.PNG)

 
![CHEESE!](steps/keycloak2.PNG.PNG)
 
 Utilisateur not connecté
 
  ![CHEESE!](steps/not authenticated.PNG)

  Utilisateur connecté
 
  ![CHEESE!](steps/authenticated_angular.PNG)
 
 
 
 
 
 
