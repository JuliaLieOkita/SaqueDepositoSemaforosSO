package view;

import java.util.concurrent.Semaphore;

import controller.ThreadTransacao;

public class Main {

	public static void main(String[] args) {
		
		float saldo;
		float valor;

		int permissaoSaque = 1;
		Semaphore semaforoSaque = new Semaphore(permissaoSaque);

		int permissaoDeposito = 1;
		Semaphore semaforoDeposito = new Semaphore(permissaoDeposito);

		for (int idConta = 1; idConta < 21; idConta++) {
			saldo = (float) ((Math.random() * 5000) + 0);
			valor = (float) ((Math.random() * 5000) + 1);
			Thread tTransacao = new ThreadTransacao(idConta, saldo, valor, semaforoSaque, semaforoDeposito);
			tTransacao.start();
		}

	}

}