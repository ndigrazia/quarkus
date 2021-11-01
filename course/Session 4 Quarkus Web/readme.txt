APIS publicas:
https://github.com/public-apis/public-apis

mvn io.quarkus:quarkus-maven-plugin:1.3.1.Final:create -DprojectGroupId="tech.donau.course" -DprojectArtifactId=crypto-price -DclassName="tech.donau.crypto.CryptoCurrencyResource" -Dpath="/crypto" -Dextensions="rest-client, resteasy-jsonb"


REST CLient:

URL used:
https://www.coinlore.com/cryptocurrency-data-api

  "data": [
    {
      "id": "90",
      "symbol": "BTC",
      "name": "Bitcoin",
      "nameid": "bitcoin",
      "rank": 1,
      "price_usd": "6456.52",
      "percent_change_24h": "-1.47",
      "percent_change_1h": "0.05",
      "percent_change_7d": "-1.07",
      "price_btc": "1.00",
      "market_cap_usd": "111586042785.56",
      "volume24": 3997655362.9586277,
      "volume24a": 3657294860.710187,
      "csupply": "17282687.00",
      "tsupply": "17282687",
      "msupply": "21000000"
    },
  "info": {
    "coins_num": 1969,
    "time": 1538560355
  }
  
  GET https://api.coinlore.net/api/tickers/
  GET https://api.coinlore.net/api/ticker/?id=90
  
  
  OpenAPI
  add ./mvnw quarkus:add-extension -Dextensions="openapi"
  
  application.properties
	quarkus.smallrye-openapi.path=/swagger
	mp.openapi.scan.disable=true
	quarkus.swagger-ui.always-include=true
	quarkus.swagger-ui.path=/public-api
  
  -----------------------------------------------
  
  Setting limit to hhtp:
  
  application.properties
  
  Set a new root path
  quarkus.http.root-path=/api
  
  http://localhost:8080/api
  
  Set body size 
  quarkus.http.limits.max-body-size=3M
  
  Set header size 
  quarkus.http.limits.max-header-size=20k
  
  -----------------------------------------------------------
  Using SSL:
  
  mvn io.quarkus:quarkus-maven-plugin:1.3.1.Final:create -DprojectGroupId=tech.donau -DprojectArtifactId=hello -DclassName="tech.donau.GreetingResource" -Dpath="/hello"

  install in our enviroment:
		sudo apt install certbot
  
  certbot certonly -a manual --preferred-challenges dns -d your.domain.com

  certbot certonly -a manual --preferred-challenges dns -d app-demo-quarkus-ssl.herokuapp.com
	
  -----------------------------------------------------------
  
  sudo openssl rand -out /home/vagrant/.rnd -hex 256
  sudo openssl req -newkey rsa:2048 -new -nodes -x509 -days 365 -keyout key.pem -out cert.pem
  openssl version -d -> OPENSSLDIR: "/usr/lib/ssl"
  
  openssl pkcs12 -export -out bundle.pfx -inkey key.pem -in cert.pem -password pass:testtest22
  
  
  +54 11 4743-4289
+54 5491161866072
  Cariló Paradise Apart
  recep1paradise@gmail.com
  lun, 17 ene 2022 a sáb, 22 ene 2022
  lun, 24 ene 2022 a sáb, 29 ene 2022
  
  quarkus.http.port=80
  quarkus.http.ssl-port=443
  quarkus.http.ssl.certificate.key-store-file=/etc/letsencrypt/live/your.domain.com/bundle.pfx
  quarkus.http.ssl.certificate.key-store-file-type=PKCS12
  quarkus.http.ssl.certificate.key-store-password=testtest22
  ---------------------------------------------------------------------------------------------

  
A partir de las 16:00
Fecha de salida

Estancia de 5 noches 