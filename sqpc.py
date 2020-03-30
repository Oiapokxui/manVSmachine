"""
/*********************************************************************/
/**   Soma dos Quadrados de 4 primos consecutivos                   **/
/**                                                                 **/
/**   Oiapokxui           enrique.emanuel@usp.br                    **/
/**                                                                 **/
/**   30/03/2020                                                    **/
/*********************************************************************/


	Modo 1 : Calcular se um numero é igual à soma dos quadrados
	de 4 outros numeros
	modo 2: Verificar se um numero é igual à soma dos quadrados 
	de 4 primos consecutivos
"""

"""
	Verifica se a igualdade n == n1*n1 + n2*n2 + n3*n3 + n4*n4
	eh verdadeira
"""
def modoum (numero, n1, n2, n3, n4):
	return ( n == n1*n1 + n2*n2 + n3*n3 + n4*n4 )

"""
	Recebe um valor e verifica se ele h igual a 
	soma dos quadrados de quatro primos consecutivos
	Retorna True ou false
	Parametros:
		'numero' - Numero inteiro a ser testado
"""
def mododois(numero):
	meio = int ((n ** (1/2))/ 2)
	upperbounds(meio)
	lowerbounds(meio)
	return ehvdd()
	
"""
	Com base em um numero inicial, calcula os 3
	numeros primos acima desse numero (Incluindo a si
	mesmo, caso o parametro seja primo). Armazena, em ordem
	crescente, os primos nas variaveis globais ub1, ub2, ub3,
	respectivamente.
	Parametro:
		'anterior' - base inicial de calculos
"""
def upperbounds(anterior):
	indexOF4 = 1
	# Calculo dos primos maiores que sqrt(n)/ 2
	while (indexOF4 <= 3) :
		#print("O numero analisado agora é " + str(anterior))
		for i in range(2, anterior):
			if anterior % i == 0:
				break
			elif ( i == (anterior - 1) ):
				if indexOF4  == 1 :
					global ub1
					ub1 = anterior
					#print(ub1)
					indexOF4 += 1
					break
				if indexOF4  == 2 :
					global ub2
					ub2 = anterior
					#print(ub2)
					indexOF4 += 1
					break
				if indexOF4  == 3 :
					global ub3
					ub3 = anterior
					#print(ub3)
					indexOF4 += 1
					break
			if (i >= 2): i+=1
			
		anterior += 1

"""
	Com base em um numero inicial, calcula os 3
	numeros primos acima desse numero. Armazena, em ordem
	decrescente, os primos nas variaveis globais lb1, lb2, lb3,
	respectivamente.
	Parametro:
		'anterior' - base inicial de calculos
"""
def lowerbounds(anterior):
	# Calculo dos primos menores que sqrt(n)/ 2
	indexOF4 = 1
	anterior -= 1
	while (indexOF4 <= 3 and anterior>0) :
		#print("O numero analisado agora é " + str(anterior))
		for i in range(1, anterior):
			if anterior % i == 0 and i != 1:
				break
			elif ( i == (anterior - 1) ):
				if indexOF4  == 1 :
					global lb1
					lb1 = anterior
					#print(lb1)
					indexOF4 += 1
					break
				if indexOF4  == 2 :
					global lb2
					lb2 = anterior
					#print(lb2)
					indexOF4 += 1
					break
				if indexOF4  == 3 :
					global lb3
					lb3 = anterior
					#print(lb3)
					indexOF4 += 1
					break

			if (i > 2): i+=1

		anterior -= 1

"""
	Verifica, quatro a quatro em ordem consecutiva, se os 
	numeros primos armazenados em ub1, ub2, ub3, lb1, lb2, lb3 
	satisfazem a condição n == n1*n1 + n2*n2 + n3*n3 + n4*n4, 
	n sendo o numero requisitado como input, n1, n2, n3, n4
	os primos.
	Caso seja verdade alguma das igualdades, armazena os primos
	em ordem consecutiva em primo1, primo2, primo3, primo4,
	respectivamente e retorna True.
	Caso contrário, retorna False.
"""

def ehvdd():
	condicao1 = lb3*lb3 + lb2*lb2 + lb1*lb1 + ub1*ub1 == n
	condicao2 = lb2*lb2 + lb1*lb1 + ub1*ub1 + ub2*ub2 == n
	condicao3 = lb1*lb1 + ub1*ub1 + ub2*ub2 + ub3*ub3 == n
	global primo1
	global primo2
	global primo3
	global primo4
	if (condicao1) :
		primo1 = lb3
		primo2 = lb2
		primo3 = lb1
		primo4 = ub1
		return True
	elif (condicao2) :
		primo1 = lb2
		primo2 = lb1
		primo3 = ub1
		primo4 = ub2
		return True
	elif (condicao3) :
		primo1 = lb1
		primo2 = ub1
		primo3 = ub2
		primo4 = ub3
		return True
	else: return False

# Aqui declaro as variaveis globais usadas
# posteriormente
lb3 = lb2 = lb1 = ub1 = ub2 = ub3 = 0
primo1 = primo2 = primo3 = primo4 = 0
vrd = "verdadeiro"
fls = "falso"

modo = int( input("modo: ") )

if modo == 1 :
	n1 = int( input("n1:") )
	n2 = int( input("n2:") )
	n3 = int( input("n3:") )
	n4 = int( input("n4:") )

n = int( input("n:"))
if (modo == 1):
	if (modoum(n, n1, n2, n3, n4) ):
		print(vrd)
	else:
		print(fls)

elif  modo == 2: 

"""
	Para um determinado numero n, n pode ser escrito como: 
		4 * ( (sqrt(n)/ 2) * (sqrt(n)/2) ) 
	
	Ao considerar os 4 primos consecutivos cuja soma dos 
	quadrados eh igual a n, o maior primo eh MAIOR que 
	sqrt(n)/ 2 , e o menor primo eh MENOR que sqrt(n)/ 2 . 
	
	Entao, o programa parte de meio = sqrt(n)/ 2 para calcular 
	alguns primos acima e abaixo desse numero. Quando obter 
	a qtd desejada, irah calcular para todos os primos 
	consecutivos a condicao.

	A sequencia dos numeros primos sera: {lb3, lb2, lb1, ub1, ub2, ub3}
	Onde lb significa Lowerbound e ub significa Upperbound
	
	Como 87 = 2*2 + 3*3 + 5*5 + 7*7, nenhum numero abaixo
	disso pode satisfazer a condicao.
"""

	if n < 87:
		print(fls)
	else: 
		if (mododois(n)):
			print(vrd)
			print("{} {} {} {}".format(primo1, primo2, primo3, primo4))
		else:
			print(fls)



