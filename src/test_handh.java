import java.util.*;

public class test_handh {
    private static int n;
    private static ArrayList<int[]> array_of_arrays = new ArrayList<>();    //массив массивов
    private static Set randoms_set = new HashSet(); //хранение рандомных чисел для размеров массивов

    //ограничения для рандомов
    private static int min_size_of_array = 1;   //минимальный размер массива
    private static int max_size_of_array = 100; //максимальный размер массива

    private static int min_elem_of_array = 1;   //минимальный элемент в массиве
    private static int max_elem_of_array = 10;  //максимальный элемент в массиве

    public static void main(String[] args) throws Exception {
        System.out.println("Введите количество массивов n:");
        //Считываем n
        Scanner in = new Scanner(System.in);
        n = in.nextInt();

        //создаем и заполняем массивы, сохраняем их в array_of_arrays
        create_arrays();
        //сортируем массивы
        sort_arrays();
        //выводим массивы
        for (int i = 0; i < n; i++)
            System.out.println("Массив " + (i+1) + " после сортировки:" + Arrays.toString(array_of_arrays.get(i)));

    }

    public static void sort_arrays()
    {
        for (int i = 0; i < n; i++)
        {
            int[] array;
            array = array_of_arrays.get(i);
            //в задаче сказано про порядковые номера массивов, я решила считать что на нулевом месте в array_of_arrays
            //хранится первый массив и так далее (т.е. сдвиг на единичку, при выводе массивов видно)
            //если все таки подразумевалось что счет можно вести с 0, то необходимо просто (i+1) заменить на i
            if ((i+1) % 2 == 0)     //если четное - сортировка по возрастанию
                Arrays.sort(array); //здесь привожу пример применения библиотесной функции
            else  //если нечетеное - по убыванию
                quickSort(array, 0, array.length - 1);
            array_of_arrays.set(i, array);  //а здесь пример с алгоритмом quicksort с убыванием
        }
    }

    public static void create_arrays() {
        int r;
        for (int i = 0; i < n; i++)
        {
            r = randoms(min_size_of_array, max_size_of_array);
            //чтобы не было массивов одинакового размера сохраняем все значения в randoms_set
            while(randoms_set.contains(r))
                r = randoms(min_size_of_array, max_size_of_array);
            randoms_set.add(r);

            int[] array = new int[r];
            array_of_arrays.add(array_with_random(array));
        }
    }

    public static int[] array_with_random(int[] array) {
        for (int i = 0; i < array.length; i++)
            array[i] = randoms(min_elem_of_array, max_elem_of_array);
        return array;
    }

    public static int randoms(int min, int max) {
        return (int) (Math.random() * max) + min;
    }


    public static void quickSort(int[] array, int low, int high)
    {
        int middle;
        if (low < high)
        {
            middle = partition(array, low, high);
            quickSort(array, low, middle);   // сортировка левой части
            quickSort(array, middle+1, high);    // сортировка правой части
        }
    }

    public static int partition(int [ ] array, int low, int high)
    {
        int a = array[low];
        int i = low - 1;
        int j = high + 1;
        int temp;
        do
        {
            do
            {
                j--;
            } while (a > array[j]);

            do
            {
                i++;
            } while (a < array[i]);

            if (i < j)
            {

                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        } while (i < j);
        return j;
    }

}
