package jfuzzyliteexample;

import com.fuzzylite.*;
import com.fuzzylite.activation.General;
import com.fuzzylite.defuzzifier.Centroid;
import com.fuzzylite.norm.s.Maximum;
import com.fuzzylite.norm.t.Minimum;
import com.fuzzylite.rule.Rule;
import com.fuzzylite.rule.RuleBlock;
import com.fuzzylite.term.Trapezoid;
import com.fuzzylite.variable.InputVariable;
import com.fuzzylite.variable.OutputVariable;

public class Fuzzy {
    Engine engine;
    InputVariable tamanho;
    InputVariable parametro;
    OutputVariable qualidade;
    RuleBlock regras;
    
    public Fuzzy(){
        engine = new Engine();
        engine.setName("avaliacao");

        tamanho = new InputVariable();
        tamanho.setName("tamanho");
        tamanho.setRange(0.0, 35.0);
       
        tamanho.addTerm(new Trapezoid("curto", -1, 0, 5, 10)); 
        tamanho.addTerm(new Trapezoid("medio", 5, 10, 15, 20));
        tamanho.addTerm(new Trapezoid("longo", 15, 20, 25, 30));
        tamanho.addTerm(new Trapezoid("muitoLongo", 25, 30, 35, 36));
        engine.addInputVariable(tamanho);
                
        parametro = new InputVariable();
        parametro.setName("parametro");
        parametro.setRange(0.0, 14.0);
       
        parametro.addTerm(new Trapezoid("baixo", -1, 0, 2, 4)); 
        parametro.addTerm(new Trapezoid("medio", 2, 4, 6, 8));
        parametro.addTerm(new Trapezoid("alto", 6, 8, 10, 12));
        parametro.addTerm(new Trapezoid("muitoAlto", 10, 12, 14, 15));
        engine.addInputVariable(parametro);

        qualidade = new OutputVariable();
        qualidade.setName("qualidade");
        qualidade.setRange(0.0, 10.0);
        
        qualidade.setAggregation(new Maximum());// como vai unir os intecendentes das regras
        qualidade.setDefuzzifier(new Centroid(100));//como vai defuzificar
        qualidade.setDefaultValue(0);//se não for ativado nenhuma regra
        
        qualidade.addTerm(new Trapezoid("excelente", -1, 0, 1, 2));
        qualidade.addTerm(new Trapezoid("muitoBom", 1, 2, 3, 4));
        qualidade.addTerm(new Trapezoid("bom", 3, 4, 5, 6));
        qualidade.addTerm(new Trapezoid("regular", 5, 6, 7, 8));
        qualidade.addTerm(new Trapezoid("preocupante", 7, 8, 9, 10));
        engine.addOutputVariable(qualidade);

        regras = new RuleBlock();
        regras.setName("regras");
        regras.setConjunction(new Minimum());//interção
        regras.setDisjunction(new Maximum());//união
        regras.setImplication(new Minimum());//implicação
        regras.setActivation(new General());//ativação
        regras.addRule(Rule.parse("if tamanho is curto and parametro is baixo then qualidade is excelente", engine));
        regras.addRule(Rule.parse("if tamanho is curto and parametro is medio then qualidade is muitoBom", engine));
        regras.addRule(Rule.parse("if tamanho is curto and parametro is alto then qualidade is bom", engine));
        regras.addRule(Rule.parse("if tamanho is curto and parametro is muitoAlto then qualidade is regular", engine));
        regras.addRule(Rule.parse("if tamanho is medio and parametro is baixo then qualidade is muitoBom", engine));
        regras.addRule(Rule.parse("if tamanho is medio and parametro is medio then qualidade is bom", engine));
        regras.addRule(Rule.parse("if tamanho is medio and parametro is alto then qualidade is regular", engine));
        regras.addRule(Rule.parse("if tamanho is medio and parametro is muitoAlto then qualidade is regular", engine));
        regras.addRule(Rule.parse("if tamanho is longo and parametro is baixo then qualidade is bom", engine));
        regras.addRule(Rule.parse("if tamanho is longo and parametro is medio then qualidade is regular", engine));
        regras.addRule(Rule.parse("if tamanho is longo and parametro is alto then qualidade is regular", engine));
        regras.addRule(Rule.parse("if tamanho is longo and parametro is muitoAlto then qualidade is preocupante", engine));
        regras.addRule(Rule.parse("if tamanho is muitoLongo and parametro is baixo then qualidade is regular", engine));
        regras.addRule(Rule.parse("if tamanho is muitoLongo and parametro is medio then qualidade is regular", engine));
        regras.addRule(Rule.parse("if tamanho is muitoLongo and parametro is alto then qualidade is preocupante", engine));
        regras.addRule(Rule.parse("if tamanho is muitoLongo and parametro is muitoAlto then qualidade is preocupante", engine));
        engine.addRuleBlock(regras);
        
    }
        
    public float[] tratamento(float tamanho, float parametro){
        float[] resultado = new float[5];
        
        InputVariable tamanhoinput = engine.getInputVariable("tamanho");
        InputVariable parametroinput = engine.getInputVariable("parametro");
        OutputVariable qualidadeoutput = engine.getOutputVariable("qualidade");

        tamanhoinput.setValue(tamanho);
        parametroinput.setValue(parametro);
        engine.process();
        
       // System.out.println("Tamanho: " + tamanhoinput.getValue() + "\nParametro:  " + parametroinput.getValue() + "\nQualidade: " + qualidadeoutput.fuzzyOutputValue());
          
        //Tratamento da string para conversão em float
      // System.out.println("\n*=====* Tratamento da String *=====*\n");
  
        //Retirada do da virgula pelo ponto para conversao de char para decimal
        String correcao = qualidadeoutput.fuzzyOutputValue().replace(',', '.');
       // System.out.println("Excelente:" + correcao);
      
        //Qualidade Excelente
       resultado[0] = Float.parseFloat(correcao.substring(0, 5)); 
       // System.out.println("Excelente:" + a);
         
        //Qualidade Muito Bom
       resultado[1] = Float.parseFloat(correcao.substring(18,23)); 
       // System.out.println("Muito Bom:" + b);
         
        //Qualidade Bom
       resultado[2] = Float.parseFloat(correcao.substring(34,40)); 
       // System.out.println("Bom:" + c);
   
        //Qualidade Regular
        resultado[3] = Float.parseFloat(correcao.substring(46,52)); 
        //System.out.println("Regular:" + d);
      
          //Qualidade Preocupante
        resultado[4] = Float.parseFloat(correcao.substring(62,68)); 
        //System.out.println("Preocupante:" + e);
        
        return resultado;
    }
    
}
