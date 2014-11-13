package juegovida;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author angieShates
 */
public class JuegoVida { 
    JFrame Interfaz;//Formulario
    JPanel contenedor= new JPanel();// Panel
    
    int[][]  tableroPrincipal,tableroSiguiente,auxiliar;//tablero

    int fila=50,columna=50;//Cantidad Filas y Columnas
    int tiempo=100; //Tiempo de Espera
    
    JButton[][] celdas = new JButton[fila][columna];//Celdas

    public JuegoVida()//Constructor
    {}
    public void Ventana(){//Personalizar interfaz
        //Panel [llenado del panel y color de los botones}
        contenedor.setLayout(new GridLayout(fila,columna));
        for(int i = 0 ; i < fila ; i++)
        {
            for(int j = 0 ; j < columna ; j++)
            {
                JButton celda = new JButton();
                celda.setSize(8, 8);
                celdas[i][j] = celda;
                celdas[i][j].setBackground(Color.BLACK);
                contenedor.add(celdas[i][j]);
            }
        }
        //Ventana caracteristicas
        Interfaz =new JFrame("JuegoVida");
        Interfaz.setSize(700, 700);
        Interfaz.add(contenedor);
        Interfaz.setVisible(true);
        Interfaz.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    };
    
   private void CambioTablero(int[][]tcambio)//Cambio Colores
    {
        for(int i = 0 ; i < fila ; i++)
        {
            for(int j = 0; j < columna ; j++)
            {
                if(tcambio[i][j] == 1)
                {
                    celdas[i][j].setBackground(Color.CYAN);
                }
                
                if(tcambio[i][j] == 0)
                {
                    celdas[i][j].setBackground(Color.BLACK);
                }           
            }
        }
    }
   
    private void inicializa(int[][] tablero)
    {
        for(int i = 0 ; i < fila ; i++)
        {
            for(int j= 0 ; j < columna ; j++)
            {
                tablero[i][j] = 0;
            }
        }
    }
    
    private void vida(){
        for(int j= 0 ; j < 500 ; j++)
        {
                int y = (int)Math.floor(Math.random()*(0-fila+1)+fila);
                int x = (int)Math.floor(Math.random()* (0-columna+1)+columna);
                tableroPrincipal[y][x] = 1;
        }        
    }
    
    @SuppressWarnings("empty-statement")
    private void juego(){
        int noVecino=0, estadoVecino=0;
        int estado=0;
        int fi=0,ci=0,ff=0,cf=0;
        int f=0,c=0;
        tableroPrincipal = new int[fila][columna];
        tableroSiguiente = new int[fila][columna];
        auxiliar = new int[fila][columna];
        
        inicializa(tableroPrincipal);
        vida();
        CambioTablero(tableroPrincipal);
        
        
        do{
            for (int i=0;i<fila;i++)//fila
            {
                for (int j=0;j<columna;j++)//columna
                {
                    noVecino=0;
                    estado = tableroPrincipal[i][j];
                    //Filas
                    fi=i-1; ff=i+1; 
                      if(i==0){ fi=i;ff=i+1;};
                      if(i==fila-1){ fi=i-1;ff=i;};
                    //Columnas
                    ci=j-1; cf=j+1; 
                      if(j==0){ ci=j;cf=j+1;};
                      if(j==columna-1){ ci=j-1;cf=j;};

                      for (f=fi;f<=ff;f++)
                      {
                         for (c=ci;c<=cf;c++)
                         {
                             estadoVecino=tableroPrincipal[f][c];
                             if(!(f==i && c==j))
                             {
                                if (estadoVecino == 1) { noVecino++;}
                             }
                         }
                      }                    
                      if(noVecino==3 && estado == 1|| noVecino==2 && estado == 1){tableroSiguiente[i][j]=1;}
                      if(noVecino<2  && estado == 1 || noVecino>3 && estado == 1){ tableroSiguiente[i][j]=0; }
                      if(noVecino==3 && estado == 0){tableroSiguiente[i][j]=1;}
                }
            }
            auxiliar = tableroPrincipal;
            tableroPrincipal = tableroSiguiente;
            tableroSiguiente = auxiliar;         
            try 
            {
                Thread.currentThread().sleep(tiempo);
             } 
            catch (InterruptedException ie) 
            {
               System.out.println("Exception " + ie.toString() );
            }

            CambioTablero(tableroSiguiente);
            inicializa(tableroSiguiente);
            
        }while(true); 
    }
    

    public static void main(String[] args) {
        // TODO code application logic here
        JuegoVida jvida = new JuegoVida();
        jvida.Ventana();
        jvida.juego();
    }
}
