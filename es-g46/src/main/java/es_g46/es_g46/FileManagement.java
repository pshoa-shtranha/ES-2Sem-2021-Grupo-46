package lalss;

import java.io.File;

public class FileManagement extends Thread {

	private int[] id;
	private File files;
	private String[][] board;
	
	public FileManagement(int[] id, File files, String[][] board) {
		
		this.id = id;
		this.files = files;
		this.board = board;
		
	}

	public void run() {
		
		try {
			
			for(int i = 0; i < id.length; i++) {
				
				if(id[i] == 1) {
					
					switch(i) {
					
						case 1:
							//numberLinesClass();
							//LOC_class
							break;
						case 2:
							//numberMethodsClass();
							//NOM_class
							break;
						case 3:
							//numberLinesMethod();
							//LOC_method
							break;
						case 4:
							//cyclesMethod();
							//CYCLO_method
							break;
						case 5:
							//cyclesClass();
							//WMC_class
							break;
					}
				}
			}
			
		} catch (InterruptedException e) {
			
			System.out.println("Thread: "+id+" foi interrompida!");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		
		//tem de ser criada outra classe que possa ser inicializada com com o array de ficheiros e com o array das opcoes
		//selecionadas pelo utilizador
		//o main desta classe passa inteiramente para a nova classe e o objeto e inicializado com o array de ficheiros e com
		//o array de int ja preenchidos
		File[] files = new File[5];
		int[] id = new int[5];
		Thread[] multiFiles = new Thread[files.length];
		String[][] board = new String[files.length][id.length];
		for(int i = 0; i < multiFiles.length; i++) {
			
			multiFiles[i] = new FileManagement(id, files[i], board);
		}
		
		for(Thread t:multiFiles) {
			
			t.start();
		}
		
		for(Thread t:multiFiles) {
			
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//chamar metodo para imprimir os resultados que estao no array board
		System.out.println("O programa terminou!");

	}

}

