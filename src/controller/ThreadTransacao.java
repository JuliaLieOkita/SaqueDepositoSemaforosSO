package controller;

import java.util.concurrent.Semaphore;

public class ThreadTransacao extends Thread {

	private int idConta;
	private float saldo;
	private float valor;
	private Semaphore semaforoSaque, semaforoDeposito;

	public ThreadTransacao(int idConta, float saldo, float valor, Semaphore semaforoSaque, Semaphore semaforoDeposito) {
		this.idConta = idConta;
		this.saldo = saldo;
		this.valor = valor;
		this.semaforoSaque = semaforoSaque;
		this.semaforoDeposito = semaforoDeposito;
	}

	@Override
	public void run() {
		try {
			semaforoSaque.acquire();
			saque();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforoSaque.release();
		}

		try {
			semaforoDeposito.acquire();
			deposito();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforoDeposito.release();
		}
	}

	private void saque() {
		int tempo = (int) ((Math.random() * 200) + 100);

		if (valor > saldo) {
			System.out.println("Conta #" + idConta + ", saldo insuficiente para saque! Saldo: " + saldo);
		} else {
			System.out.println("Conta #" + idConta + ", seu saldo é de: " + saldo + ". Processando saque...");
			try {
				sleep(tempo);
				saldo -= valor;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Saque da conta #" + idConta + " no valor de " + valor + " realizado com sucesso!");
			System.out.println("Saldo da conta #" + idConta + ": " + saldo);
		}
	}

	private void deposito() {
		int tempo = (int) ((Math.random() * 200) + 100);

		System.out.println("Conta #" + idConta + ", processando deposito...");
		try {
			sleep(tempo);
			saldo += valor;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Deposito na conta #" + idConta + " no valor de " + valor + " realizado com sucesso!");
		System.out.println("Saldo da conta #" + idConta + ": " + saldo);
	}

}