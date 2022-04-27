#include <Adafruit_GFX.h>         // Core graphics library
#include <Adafruit_ST7789.h> // Hardware-specific library for ST7789
#include <SdFat.h>                // SD card & FAT filesystem library
#include <Adafruit_SPIFlash.h>    // SPI / QSPI flash library
#include <Adafruit_ImageReader.h> // Image-reading functions
#include <SoftwareSerial.h> //Bluetooth module
//Softwareserial stammer fra bluetoothmodulet. Vi deklarerer, at Blue modtager og transmiterer på hhvs. digital pin 2 og 3.
SoftwareSerial Blue(2, 3);
// Her deklarerer vi global variables, som vi 
long int data;
long int password1 = 92;// light on, eller i vores tilfælde, inkrementer billede med 1.
long int password2 = 79; // light off
int counter = 1; //Counter er lig nummeret på billedet der skal displayes; Hver eneste gang bluetooth modtager kommunikation, inkrementeres counter med 1. Når counter 4, reduceres counter til 1.
boolean newData = false; //Evaluerer om data er modtaget; Hvis ja, skal counter inkrementeres med 1.

//Liste over billeder på SD-kortet (Det er formateret i MS-DOS Fat):
// image1.bmp
// image2.bmp
// image3.bmp
// image4.bmp
// Husk at billeder skal være i opløsningen 240*280.
// Comment out the next line to load from SPI/QSPI flash instead of SD card:
#define USE_SD_CARD
#define SD_CS    4 // SD card select pin
#define TFT_CS  10 // TFT select pin
#define TFT_DC   8 // TFT display/command pin
#define TFT_RST  9 // Or set to -1 and connect to Arduino RESET pin

#if defined(USE_SD_CARD)
  SdFat                SD;         // SD card filesystem
  Adafruit_ImageReader reader(SD); // Image-reader object, pass in SD filesys
#else
  // SPI or QSPI flash filesystem (i.e. CIRCUITPY drive)
  #if defined(__SAMD51__) || defined(NRF52840_XXAA)
    Adafruit_FlashTransport_QSPI flashTransport(PIN_QSPI_SCK, PIN_QSPI_CS,
      PIN_QSPI_IO0, PIN_QSPI_IO1, PIN_QSPI_IO2, PIN_QSPI_IO3);
  #else
    #if (SPI_INTERFACES_COUNT == 1)
      Adafruit_FlashTransport_SPI flashTransport(SS, &SPI);
    #else
      Adafruit_FlashTransport_SPI flashTransport(SS1, &SPI1);
    #endif
  #endif
  Adafruit_SPIFlash    flash(&flashTransport);
  FatFileSystem        filesys;
  Adafruit_ImageReader reader(filesys); // Image-reader, pass in flash filesys
#endif

Adafruit_ST7789      tft    = Adafruit_ST7789(TFT_CS, TFT_DC, TFT_RST);
Adafruit_Image       img;        // An image loaded into RAM
int32_t              width  = 0, // BMP image dimensions
                     height = 0;

void setup(void) {

Serial.begin(9600); //Begynder kommunikation på serial port, med en baud-hastighed på 9600. Ikke nødvendigt, men kun til at hjælpe med at debugge bluetooth
Blue.begin(9600); //Gør det samme for bluetooth

#if !defined(ESP32)
  while(!Serial);       // Wait for Serial Monitor before continuing
#endif

  tft.init(240, 280);           // Init ST7789 280x240
  // The Adafruit_ImageReader constructor call (above, before setup())
  // accepts an uninitialized SdFat or FatFileSystem object. This MUST
  // BE INITIALIZED before using any of the image reader functions!
  Serial.print(F("Initializing filesystem..."));
#if defined(USE_SD_CARD)
  // SD card is pretty straightforward, a single call...
  if(!SD.begin(SD_CS, SD_SCK_MHZ(10))) { // Breakouts require 10 MHz limit due to longer wires
    Serial.println(F("SD begin() failed"));
    for(;;); // Fatal error, do not continue
  }
#else
  // SPI or QSPI flash requires two steps, one to access the bare flash
  // memory itself, then the second to access the filesystem within...
  if(!flash.begin()) {
    Serial.println(F("flash begin() failed"));
    for(;;);
  }
  if(!filesys.begin(&flash)) {
    Serial.println(F("filesys begin() failed"));
    for(;;);
  }
#endif
  Serial.println(F("OK!"));

  // Fill screen blue. Not a required step, this just shows that we're
  // successfully communicating with the screen.
  tft.fillScreen(ST77XX_BLUE);
  delay(2000); // Pause 2 seconds before moving on to loop()
}

void loop() {

  //while(Blue.available()==0) ; Gør intet, før bluetooth er connected.

 if(Blue.available()>0)  //Hvis bluetooth er tilgængelig, send da en værdi til Serial;
{

Serial.println("1234");
  
data = Blue.parseInt(); // Modtag den transmiterede bluetooth værdi, og opbevar den i data
  } 
Serial.println(data); // Udskriv den modtagne værdi til serial - ikke nødvendigt, men praktisk til debugging. 

  Updatecounter();
  showdata();
  printing();
  checking();
}

void Updatecounter(){
if (data == password1) //Hvis den modtagne værdi er korrekt, sæt newdata til true
   {
//    char newInput =  Serial.read();
    newData = true;
  }
}

void showdata(){
  if (newData == true){ //Hvis newdata er true, inkrementer da counter med 1, og sæt newdata til false
    counter = counter + 1;
    newData = false;
    data=0; //Sæt data til 0, dermed "cleares" den senest modtagne bluetooth-værdi
  }  
}

void printing(){
ImageReturnCode stat; // Status from image-reading functions
  ///Tegn det billede der svarer til counter
  while (counter == 1){
     // Serial.println(F("Loading image1.bmp to screen..."));
  stat = reader.drawBMP("/image1.bmp", tft, 0, 0);
  break;
  }
  

while (counter == 2) {
   //Serial.println(F("Loading image2.bmp to screen..."));
  stat = reader.drawBMP("/image2.bmp", tft, 0, 0);  
  break;
}

while (counter == 3) {
  //  Serial.println(F("Loading image3.bmp to screen..."));
  stat = reader.drawBMP("/image3.bmp", tft, 0, 0);  
  break;
}

while (counter == 4) {
 // Serial.println(F("Loading image4.bmp to screen..."));
  stat = reader.drawBMP("/image4.bmp", tft, 0, 0);  
  break;
}

}

void checking(){
  if (counter > 4) {
    counter = 1;
    //Sætter counter til 1, i tilfælde af at counter er over 5, eller, returner til det første billede.
  }  
}
