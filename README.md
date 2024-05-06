# Requerimientos técnicos para construir el proyecto
desarrollado en: 
- Android Gradle Plugin 8.3.2
- Gradle version 8.4
- JDK 17.0.9

# Capas propuestas
Se desarrolla con una arquitectura Clean y patrón de presentación MVVM:
Capa de Data: Se responsabiliza de configurar las fuentes de información (API, DDBB, etc) y de los modelos de datos, a demás de los repositorios que hacen uso de dichas fuentes de datos para proveer información.
Capa de Domain: Se hace uso de los repositorios para desarrollar la lógica de negocio.
Capa de UI: Se desarrolla los ViewModel y las vistas. El viewModel por medio de los casos de uso, obtiene la información solicitada por las vistas y 
envía dicha información por medio de observables a los cuales las vistas están previamente suscritas.

# ScreenShots
<img src="images/CountriesApp1.jpg" width = "300" height="600">  
<img src="images/CountriesApp2.jpg" width = "300" height="600">  
<img src="images/CountriesApp3.jpg" width = "300" height="600">  
