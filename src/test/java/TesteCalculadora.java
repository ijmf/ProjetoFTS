import br.com.iterasys.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;


public class TesteCalculadora {

    @Test
    public void testeSomarDoisNumeros() {

        //Configura
        double num1 = 7;
        double num2 = 5;

        //Valores de saída
        double resultadoEsperado = 12;

        //Executa
        double resultadoAtual = Calculadora.somarDoisNumeros(num1, num2);

        //Valida
        assertEquals(Double.valueOf(resultadoEsperado), Double.valueOf(resultadoAtual));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "7, 5, 12",
            "56, 44, 100",
            "10, 0, 10",
            "15, -5, 10",
            "-8, -6, -14"
    }, delimiter = ',')

    public void testeSomarDoisNumerosLendoLista(String txtNum1, String txtNum2, String resultadoEsperado) {

        //Configura
       // Os dados de entrada vem da lista.

        //Valores de saída
        //double resultadoEsperado = 12;

        //Executa
        double resultadoAtual = Calculadora.somarDoisNumeros(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));

        //Valida
        assertEquals(Double.valueOf(resultadoEsperado), Double.valueOf(resultadoAtual));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "csv/massaSomar.csv")

    public void testeSomarDoisNumerosLendoArquivo(String txtNum1, String txtNum2, String resultadoEsperado) {

        //Configura
        // Os dados de entrada vem da lista.

        //Valores de saída
        //double resultadoEsperado = 12;

        //Executa
        double resultadoAtual = Calculadora.somarDoisNumeros(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));

        //Valida
        assertEquals(Double.valueOf(resultadoEsperado), Double.valueOf(resultadoAtual));
    }

    @Test
    public void testeSubtrairDoisNumeros() {

        //Configura
        double num1 = 7;
        double num2 = 5;

        //Valores de saída
        double resultadoEsperado = 2;

        //Executa
        double resultadoAtual = Calculadora.subtrairDoisNumeros(num1, num2);

        //Valida
        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void testeMultiplicarDoisNumeros() {

        //Configura
        double num1 = 7;
        double num2 = 5;

        //Valores de saída
        double resultadoEsperado = 35;

        //Executa
        double resultadoAtual = Calculadora.multiplicarDoisNumeros(num1, num2);

        //Valida
        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void testeDividirDoisNumeros() {

        //Configura
        double num1 = 10;
        double num2 = 5;

        //Valores de saída
        double resultadoEsperado = 2;

        //Executa
        double resultadoAtual = Calculadora.dividirDoisNumeros(num1, num2);

        //Valida
        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void testeDividirPorZero() {

        //Configura
        double num1 = 10;
        double num2 = 5;

        //Valores de saída
        double resultadoEsperado = 2;

        //Executa
        double resultadoAtual = Calculadora.dividirDoisNumeros(num1, num2);

        //Valida
        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void testeDividirDoisNumerosInteiros() {

        //Configura
        int numA = 8;
        int numB = 0;
        String resultadoEsperado = "Não é possível dividir por zero";

        //Valores de saída

        //int resultadoEsperado = 5;

        //Executa
        String resultadoAtual = Calculadora.dividirDoisNumerosInteiros(numA, numB);

        //Valida
        assertEquals(resultadoEsperado, resultadoAtual);
        System.out.println(numA + " / " + numB + " = " + resultadoAtual);
        System.out.println("O resultado esperado: " + resultadoEsperado);
    }

}
