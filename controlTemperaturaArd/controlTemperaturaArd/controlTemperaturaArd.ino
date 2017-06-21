#include <ArduinoJson.h>
#include <OneWire.h>
#include <DallasTemperature.h>
#include <tempo.h>

//Relays
#define RELAY1 2
#define RELAY1 3
#define RELAY1 4

#define HISTERESIS 0.6

//Sensores Temperatura
#define BUSONEWIRE1 6
#define BUSONEWIRE1 7
#define BUSONEWIRE1 8

OneWire oneWire1(BUSONEWIRE1);
DallasTemperature sensor1(&oneWire1);

OneWire oneWire1(BUSONEWIRE1);
DallasTemperature sensor2(&oneWire2);

OneWire oneWire1(BUSONEWIRE1);
DallasTemperature sensor3(&oneWire3);

Tempo t_temp(30*1000); // temporizador para la lectura de temperatura

//---Agregado Renzo

struct SensorSetting   //generamos la estructura point para poder guardar la posicion de los motores en cada punto.
{
  int  sensor=0;   //pulsos motor joint 1
  int  tempSet= 0;
};

int eeAdd = 0;

//-----------------

void setup() {  
  
  Serial.begin(9600); 
  pinMode(RELAY1,OUTPUT);
  pinMode(RELAY2,OUTPUT);
  pinMode(RELAY3,OUTPUT);

  

}

void loop() {

   if (t_temp.state())
  {
    //Sensor 1
        sensor1.requestTemperatures();
        float temp1=sensor1.getTempCByIndex(0);
        
        if(temp1 > tempSet1+HISTERESIS){
          digitalWrite(RELAY1,HIGH);
          Serial.print("ON  ");
          Serial.println(temp1);
         } else{
            if ( temp1<tempSet1-HISTERESIS){  
              Serial.print("OFF  ");
              Serial.println(temp1);
              digitalWrite(RELAY1,LOW);
              }
          }  
    //Sensor 2
      sensor2.requestTemperatures();
      float temp2=sensor2.getTempCByIndex(0);
        
      if(temp2 > tempSet2+HISTERESIS){
         digitalWrite(RELAY2,HIGH);
         Serial.print("ON  ");
         Serial.println(temp2);
       } else{
          if ( temp2<tempSet2-HISTERESIS){  
            Serial.print("OFF  ");
            Serial.println(temp2);
            digitalWrite(RELAY2,LOW);
            }
       }  
      //Sensor 3
      sensor3.requestTemperatures();
      float temp3=sensor3.getTempCByIndex(0);
        
      if(temp3 > tempSet3+HISTERESIS){
         digitalWrite(RELAY2,HIGH);
         Serial.print("ON  ");
         Serial.println(temp2);
       } else{
          if ( temp1<tempSet1-HISTERESIS){  
            Serial.print("OFF  ");
            Serial.println(temp3);
            digitalWrite(RELAY3,LOW);
            }
       } 
  }
}
