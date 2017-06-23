#include <OneWire.h>
#include <DallasTemperature.h>
#include <ArduinoJson.h>
#include <tempo.h>
#include <EEPROM.h>


//Relays
#define RELAY1 2
#define RELAY2 3
#define RELAY3 4

#define HISTERESIS 0.6

//Sensores Temperatura
#define BUSONEWIRE1 6
#define BUSONEWIRE2 7
#define BUSONEWIRE3 8

OneWire oneWire1(BUSONEWIRE1);
DallasTemperature sensor1(&oneWire1);

OneWire oneWire2(BUSONEWIRE2);
DallasTemperature sensor2(&oneWire2);

OneWire oneWire3(BUSONEWIRE3);
DallasTemperature sensor3(&oneWire3);

Tempo t_temp(2*1000); // temporizador para la lectura de temperatura

//---Agregado Renzo

struct SensorSetting   //generamos la estructura point para poder guardar la posicion de los motores en cada punto.
{
  const int  sensor1;  
  char descripcion1[20];
  float valor1;
  float  valorSet1;

  const int  sensor2;  
  char descripcion2[20];
  float valor2;
  float  valorSet2;

  const int  sensor3;  
  char descripcion3[20];
  float valor3;
  float  valorSet3;
};


SensorSetting sensorSetting = {1,"Sensor1",0,10, 2,"Sensor2",0,15, 3,"Sensor3",0,20 };

//-----------------

void setup() {  
  
  Serial.begin(9600); 
  pinMode(RELAY1,OUTPUT);
  pinMode(RELAY2,OUTPUT);
  pinMode(RELAY3,OUTPUT);

  EEPROM.put(0,sensorSetting);
  EEPROM.get(0,sensorSetting);

 
}

void loop() {
  
    StaticJsonBuffer<250> jsonBuffer;
    JsonObject& root = jsonBuffer.createObject();
    
   if (t_temp.state())
  {
    //----------------Sensor 1---------------------------
        sensor1.requestTemperatures();
        sensorSetting.valor1 = sensor1.getTempCByIndex(0);
        
        if(sensorSetting.valor1 > sensorSetting.valorSet1+HISTERESIS){
          digitalWrite(RELAY1,HIGH);
       
         } else{
            if ( sensorSetting.valor1 <sensorSetting.valorSet1-HISTERESIS){  
                  digitalWrite(RELAY1,LOW);
         
              }
          }  
          
    JsonObject& jsonObjectSensor1 = root.createNestedObject("");
    jsonObjectSensor1["id"] = sensorSetting.sensor1;
    jsonObjectSensor1["descripcion"] = sensorSetting.descripcion1;
    jsonObjectSensor1["valor"] = sensorSetting.valor1;
    jsonObjectSensor1["valorSet"] = sensorSetting.valorSet1;
          
    //----------------Sensor 2---------------------------
      sensor2.requestTemperatures();
      sensorSetting.valor2 = sensor2.getTempCByIndex(0);
        
      if(sensorSetting.valor2 > sensorSetting.valorSet2+HISTERESIS){
         digitalWrite(RELAY2,HIGH);
 
       } else{
          if ( sensorSetting.valor2 < sensorSetting.valorSet2-HISTERESIS){  
               digitalWrite(RELAY2,LOW);
            }
       } 

    JsonObject& jsonObjectSensor2 = root.createNestedObject("");
    jsonObjectSensor2["id"] = sensorSetting.sensor2;
    jsonObjectSensor2["descripcion"] = sensorSetting.descripcion2;
    jsonObjectSensor2["valor"] = sensorSetting.valor2;
    jsonObjectSensor2["valorSet"] = sensorSetting.valorSet2;
    
     //----------------Sensor 3---------------------------
      sensor3.requestTemperatures();
      sensorSetting.valor3 = sensor3.getTempCByIndex(0);
        
      if(sensorSetting.valor3 > sensorSetting.valorSet3+HISTERESIS){
         digitalWrite(RELAY3,HIGH);
    
       } else{
          if ( sensorSetting.valor3 < sensorSetting.valorSet3-HISTERESIS){  
               digitalWrite(RELAY3,LOW);
            }
       }

    JsonObject& jsonObjectSensor3 = root.createNestedObject("");
    jsonObjectSensor3["id"] = sensorSetting.sensor3;
    jsonObjectSensor3["descripcion"] = sensorSetting.descripcion3;
    jsonObjectSensor3["valor"] = sensorSetting.valor3;
    jsonObjectSensor3["valorSet"] = sensorSetting.valorSet3;

    root.printTo(Serial);
    
  }

  if(Serial.available())
  {
      String jsonString = "";
      char character;

      while(Serial.available()) {
        character = Serial.read();
        jsonString.concat(character);
     }

     StaticJsonBuffer<200> jsonBuffer;
     JsonObject& jsonData = jsonBuffer.parseObject(jsonString);

     if (!jsonData.success())
     {
        Serial.println("parseObject() failed");
     }

     int idSensor = jsonData["id"];
     switch (idSensor)
     {
        case 1:
              strncpy(sensorSetting.descripcion1,jsonData["descripcion"],18);
              sensorSetting.valorSet1 = jsonData["valorSet"];
           break;
        case 2:
            strncpy(sensorSetting.descripcion2,jsonData["descripcion"],18);
            sensorSetting.valorSet2 = jsonData["valorSet"];
           break;
        case 3:
            strncpy(sensorSetting.descripcion3,jsonData["descripcion"],18);
            sensorSetting.valorSet3 = jsonData["valorSet"];
           break;
        default:
              break;            
     }
  }
  
}
