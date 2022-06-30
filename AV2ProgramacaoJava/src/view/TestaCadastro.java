package view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.AlunosDAO;
import model.Aluno;

public class TestaCadastro {

	public static void main(String[] args) {
		Scanner leitura = new Scanner(System.in);
		System.out.println("Sistema de alunos:");
		System.out.println();
		int opcao;
		
		System.out.println("Escolha uma opção:");
		System.out.println("1--Inserir");
		System.out.println("2--Pesquisa Geral");
		System.out.println("3--Pesquisa filtrada pela inicial do nome");
		System.out.println("4--Atualizar Aluno");
		System.out.println("5--Remover Aluno");
		System.out.println("6--Sair");
		
		opcao = leitura.nextInt();
		
		
			switch (opcao) {
			case 1:
				String nome;
				String email;
				int cpf;
				String dataNascimento;
				String naturalidade;
				String endereco;
					
				System.out.println("Digite o nome do aluno:");
				nome = leitura.next();
				System.out.println("Digite o email do aluno:");
				email = leitura.next();
				
				System.out.println("Digite o cpf do aluno:");
				cpf = leitura.nextInt();
				System.out.println("Digite a data de nascimento do aluno:");
				//ver como adicionar data depois
				dataNascimento = leitura.next();
				
				System.out.println("Digite a naturalidade do aluno:");
				naturalidade = leitura.next();
				System.out.println("Digite o endereço do aluno:");
				endereco = leitura.next();
				
				Aluno alunoInstancia = new Aluno(nome, email, cpf,dataNascimento, naturalidade, endereco);
				
				try {
					AlunosDAO dao = new AlunosDAO();
					dao.inserir(alunoInstancia);
					System.out.println("Aluno cadastrado com sucesso!");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Listando...\n");
				try {
					AlunosDAO dao = new AlunosDAO();
					List<Aluno> alunos = dao.getLista();
					for (Aluno aluno : alunos) {
						System.out.println("Id: "+aluno.getId());
						System.out.println("Nome: "+aluno.getNome());
						System.out.println("Email: "+aluno.getEmail());
						System.out.println("Cpf: "+aluno.getCpf());
						System.out.println("Data de Nascimento: (Digitar no padrão norte americano e tudo junto)"+aluno.getDataDeNascimento());
						System.out.println("Endereço: "+aluno.getEndereco());
						System.out.println("Naturalidade: "+aluno.getNaturalidade());
						
					}
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				break;
			case 3:
				String letra;
				System.out.println("Digite por qual letra deseja pesquisar:");
				letra = leitura.next();
				System.out.println("Listando...\n");
				try {
					AlunosDAO dao = new AlunosDAO();
					List<Aluno> alunos = dao.getListaFiltrado(letra);
					for (Aluno aluno : alunos) {
						System.out.println("Id: "+aluno.getId());
						System.out.println("Nome: "+aluno.getNome());
						System.out.println("Email: "+aluno.getEmail());
						System.out.println("Cpf: "+aluno.getCpf());
						System.out.println("Data de Nascimento: "+aluno.getDataDeNascimento());
						System.out.println("Endereço: "+aluno.getEndereco());
						System.out.println("Naturalidade: "+aluno.getNaturalidade());
						
					}
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				break;
				
				
			case 4:
				
				int id;
				System.out.println("Digite os dados para alteração:\n");
				System.out.println("Nome: ");
				nome = leitura.next();
				System.out.println("Email: ");
				email = leitura.next();
				System.out.println("Endereço: ");
				endereco = leitura.next();
				System.out.println("Data De Nascimento:(Digitar tudo junto e no padrão norte americano)");
				dataNascimento = leitura.next();
				System.out.println("Naturalidade: ");
				naturalidade = leitura.next();
				System.out.println("CPF: ");
				cpf = leitura.nextInt();
				System.out.println("Digite o ID do aluno: ");
				id = leitura.nextInt();
				
				Aluno aluno = new Aluno(nome, email, cpf, dataNascimento, naturalidade, endereco);
				try {
					AlunosDAO dao = new AlunosDAO();
					dao.atualizar(aluno,id);
					System.out.println("Aluno atualizado com sucesso!");
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("Digite o id do aluno que deseja excluir:");
				id = leitura.nextInt();
				try {
					AlunosDAO dao = new AlunosDAO();
					dao.excluir(id);
					System.out.println("Aluno excluido com sucesso!");
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
			
		

	}

}
