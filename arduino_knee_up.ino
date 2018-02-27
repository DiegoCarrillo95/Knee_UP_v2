#define DATA_TRANSFER_PERIOD 5
#define DATA_TRANSFER_WINDOW 5
#define PORTA_INDICADOR 8

String texto;
int analogPin = 0;
int val = 0;
bool led = 0;

void acendeLed(){
  if(!led){
     digitalWrite(LED_BUILTIN, HIGH);
     digitalWrite(PORTA_INDICADOR, HIGH);
     led = 1;
  }
}

void apagaLed(){
  if(led){
     digitalWrite(LED_BUILTIN, LOW);
     digitalWrite(PORTA_INDICADOR, LOW);
     led = 0;
  }
}

void serialFlush(){
  while(Serial.available() > 0) {
    char t = Serial.read();
  }
}  

void setup() {
        Serial.begin(38400);     
        Serial.setTimeout(5); 
        pinMode(LED_BUILTIN,OUTPUT);
        pinMode(PORTA_INDICADOR,OUTPUT);
        apagaLed();
}     

void loop() {

      //teste de verificação do arduino
       while(1){ 
        texto = Serial.readString(); 
        if (texto.length() > 0) {     
            break;
        }
       }
       if(texto.equals("knee_up")){   //teste de verificação do arduino
        Serial.write(42);   
       }   


       if(texto.equals("iniciarteste")){   //inicio de uma bateria de testes
        apagaLed();
        Serial.write(77); 
        int i = 0;
        int tempo_inicio = millis();
        int tempo; 
        while(i<((DATA_TRANSFER_WINDOW*1000)/DATA_TRANSFER_PERIOD)){
           val = analogRead(analogPin);    
           tempo = millis() - tempo_inicio;
           Serial.println(val); 
           Serial.println(tempo);   
           delay(DATA_TRANSFER_PERIOD);          
           i++;
           if(tempo>=1000) {
            acendeLed();
           }
        }
        apagaLed();
        delay(500);
        serialFlush(); 
       } 
}
