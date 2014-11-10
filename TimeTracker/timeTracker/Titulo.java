
package timeTracker;


public class Titulo{
        StringBuilder palabra;
        public Titulo(byte[] palabra){
            if ( palabra!=null && palabra.length > 0 ){
                this.palabra = new StringBuilder();
                for (byte b:palabra){
                    if ( b!=0 )
                        this.palabra.append((char)b);
                    else
                        break;
                }
            }
        }
        
        @Override
        public String toString(){
            return this.palabra.toString();
        }
    }