using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public enum Operacion
    {
        noDefinida=0,
        suma=1,
        resta=2,
        multi=3,
        division=4,
        mod=5,
        raizCuadrada=6,
        potenciaCuadrada=7,
        inverso=8,
        cambioSigno=9
    }
    public partial class Form1 : Form
    {
        private double valor1 = 0;
        private double valor2 = 0;
        Operacion operar = Operacion.noDefinida;
        bool numPulsado = false;
        bool operacionEsp = false;
        public void AgregarNumero(String num) {

            numPulsado = true;
            if (tbResultado.Text == null || tbResultado.Text == "0")
            {

                tbResultado.Text = num;

            }
            else {

                tbResultado.Text += num;
            
            }
        
        }

        public void getValor(String operador)
        {
            operacionEsp = false;
            valor1 = Convert.ToDouble(tbResultado.Text);
            lbHistorial.Text = $"{tbResultado.Text} {operador} ";
            tbResultado.Text = "0";
        }

        public void getRaiz(String operador)
        {
            operacionEsp = true;
            valor1 = Convert.ToDouble(tbResultado.Text);
            lbHistorial.Text = $"{operador}({tbResultado.Text}) ";
            imprimirResultado();
        }

        public void getCuadrado()
        {
            operacionEsp = true;
            valor1 = Convert.ToDouble(tbResultado.Text);
            lbHistorial.Text = $"sqr({tbResultado.Text}) ";
            imprimirResultado();
        }

        public void getInverso()
        {
            operacionEsp = true;
            valor1 = Convert.ToDouble(tbResultado.Text);
            lbHistorial.Text = $"1/({tbResultado.Text}) ";
            imprimirResultado();
        }

        public void getCambioSigno()
        {
            valor1 = Convert.ToDouble(tbResultado.Text);
            imprimirResultado();
        }

        public void imprimirResultado() {

            double resualtado = EjecutarOperacion();
            tbResultado.Text = resualtado.ToString();
        }
        private double EjecutarOperacion() {
            
            double resultado = 0;

            switch (operar)
            {
                case Operacion.suma:
                    resultado = valor1 + valor2;
                    break;
                case Operacion.resta:
                    resultado = valor1 - valor2;
                    break;
                case Operacion.multi:
                    resultado = valor1 * valor2;
                    break;
                case Operacion.division:
                    if (valor2 == 0)
                    {
                        MessageBox.Show("No se puede dividir entre 0.");
                        resultado = 0;
                    }
                    else
                    {
                        resultado = valor1 / valor2;
                    }
                    break;
                case Operacion.mod:
                    resultado = valor1 % valor2;
                    break;
                case Operacion.raizCuadrada:
                    resultado = Math.Sqrt(valor1);
                    break;
                case Operacion.potenciaCuadrada:
                    resultado = valor1*valor1;
                    break;
                case Operacion.inverso:
                    resultado = 1/(valor1);
                    break;
                case Operacion.cambioSigno:
                    resultado = valor1*-1;
                    break;
            }

            return resultado;
        }
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void btnNum1_Click(object sender, EventArgs e)
        {
            AgregarNumero(btnNum1.Text);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if ((valor2 == 0 && numPulsado == true) && operacionEsp == false) {

                valor2 = Convert.ToDouble(tbResultado.Text);
                if (lbHistorial.Text == "Historial")
                {
                    lbHistorial.Text = "";
                    lbHistorial.Text += valor2 + " = ";
                    imprimirResultado();
                    valor1 = 0;
                    valor2 = 0;
                    numPulsado = false;
                }
                else
                {
                    lbHistorial.Text += valor2 + " = ";
                    imprimirResultado();
                    valor1 = 0;
                    valor2 = 0;
                    numPulsado = false;
                }
            }
        }

        private void btnNum0_Click(object sender, EventArgs e)
        {
            if (tbResultado.Text == null || tbResultado.Text == "0")
            {
                return;
            }
            else
            {
                numPulsado = true;
                tbResultado.Text += btnNum0.Text;
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            operar = Operacion.multi;
            getValor("×");
        }

        private void lbHistorial_Click(object sender, EventArgs e)
        {

        }

        protected void tbResultado_TextChanged(object sender, EventArgs e)
        {

        }

        private void btnNum2_Click(object sender, EventArgs e)
        {
            AgregarNumero(btnNum2.Text);
        }

        private void btnNum3_Click(object sender, EventArgs e)
        {
            AgregarNumero(btnNum3.Text);
        }

        private void btnNum4_Click(object sender, EventArgs e)
        {
            AgregarNumero(btnNum4.Text);
        }

        private void btnNum5_Click(object sender, EventArgs e)
        {
            AgregarNumero(btnNum5.Text);
        }

        private void btnNum6_Click(object sender, EventArgs e)
        {
            AgregarNumero(btnNum6.Text);
        }

        private void btnNum7_Click(object sender, EventArgs e)
        {
            AgregarNumero(btnNum7.Text);
        }

        private void btnNum8_Click(object sender, EventArgs e)
        {
            AgregarNumero(btnNum8.Text);
        }

        private void btnNum9_Click(object sender, EventArgs e)
        {
            AgregarNumero(btnNum9.Text);
        }

        private void button4_Click(object sender, EventArgs e)
        {
            operar = Operacion.suma;
            getValor("+");
        }

        private void btnRestar_Click(object sender, EventArgs e)
        {
            operar = Operacion.resta;
            getValor("-");
        }

        private void btnDividir_Click(object sender, EventArgs e)
        {
            operar = Operacion.division;
            getValor("÷");
        }

        private void btnRaiz_Click(object sender, EventArgs e)
        {
            operar = Operacion.raizCuadrada;
            getRaiz("√");
        }

        private void btnCuadrado_Click(object sender, EventArgs e)
        {
            operar = Operacion.potenciaCuadrada;
            getCuadrado();
        }

        private void btnInverso_Click(object sender, EventArgs e)
        {
            operar = Operacion.inverso;
            getInverso();
        }

        private void btnModulo_Click(object sender, EventArgs e)
        {
            operar = Operacion.mod;
            getValor("%");
        }

        private void btnBorrar_Click(object sender, EventArgs e)
        {
            if (tbResultado.Text.Length > 1)
            {
                string txtResualtado = tbResultado.Text;
                txtResualtado = txtResualtado.Substring(0,txtResualtado.Length - 1);

                if (txtResualtado.Length == 1 && txtResualtado.Contains("-"))
                {
                    tbResultado.Text = "0";
                }
                else
                {
                    tbResultado.Text = txtResualtado;
                }
            }
            else {
                tbResultado.Text = "0";
            }
        }

        private void btnBorrarCampo_Click(object sender, EventArgs e)
        {
            
        }

        private void btnBorrarTodo_Click(object sender, EventArgs e)
        {
            tbResultado.Text = "0";
            lbHistorial.Text = "Historial";
        }

        private void btnPunto_Click(object sender, EventArgs e)
        {
            if (tbResultado.Text.Contains(".")) {
                return;
            }
            tbResultado.Text += ".";
        }

        private void btnSigno_Click(object sender, EventArgs e)
        {
            operar = Operacion.cambioSigno;
            getCambioSigno();
        }
    }
}
