package projetoSA.escolaControle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetoSA.escolaControle.model.DTO.AlunoDTO;
import projetoSA.escolaControle.model.entity.Aluno;
import projetoSA.escolaControle.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;
	
	public Aluno salvarAluno(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	public Aluno salvarAlunoDTO(AlunoDTO dto) {
		Aluno aluno = new Aluno(null, dto.getNomeAluno(), dto.getResponsavelContato(), true, null, null, null);
		return this.alunoRepository.save(aluno);
	}
	
	public void editarAluno(Aluno aluno) {
		if (alunoRepository.findById(aluno.getIdAluno()).isPresent()) {
			alunoRepository.save(aluno);
		}
	}
	
	public List<Aluno> procurarAlunos(){
		return alunoRepository.findAll();
	}
	
	public List<Aluno> procurarAlunoPorNome(String nomeAluno){
		return alunoRepository.findByNomeAlunoLike("%".concat(nomeAluno).concat("%"));
	}
	
	public Optional<Aluno> procurarAlunoPorIdParaRelatorio(Integer idAluno){
		return alunoRepository.findById(idAluno);
	}
	
	public AlunoDTO procurarAlunoPorId(Integer idAluno){
		Optional<Aluno> aluno =  alunoRepository.findById(idAluno);
		if(aluno.isPresent()) {
			AlunoDTO dto = new AlunoDTO(aluno.get());
			return dto;
		}
		return null;
	}
	
	public void excluirAluno(Integer idAluno) {
		alunoRepository.deleteById(idAluno);
	}
}
