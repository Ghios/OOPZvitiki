package Laba7;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static Hotel vacFind(int id, List<Hotel> vac){
        Iterator<Hotel> myItr = vac.iterator();
        while(myItr.hasNext()){
            Hotel t = myItr.next();
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        boolean autoMode = false;
        for(int i = 0;i < args.length;i++){
            if(args[i].equals("-auto")){
                autoMode = true;
            }
        }
        List<Hotel> vacancyList = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        if(autoMode){
            try {
                scan = new Scanner(new BufferedInputStream(new FileInputStream("automode.txt")));
            }catch(FileNotFoundException ex){
                System.err.println("Автоматичний режим не запущено!");
                scan = new Scanner(System.in);
            }
        }
        int command,id;
        while(true){
            if(autoMode){
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch(InterruptedException e){

                }
            }
            System.out.println("---------------------------------");
            System.out.println("1 - Забронювати місця в готелі");
            System.out.println("2 - Відмінити бронювання");
            System.out.println("3 - Переглянути список бронювань");
            System.out.println("4 - Меню бронювання");
            System.out.println("5 - Записати бронювання в XML-файл");
            System.out.println("6 - Витягти бронювання з XML-файлу");
            System.out.println("7 - Записати бронювання в файл(TXT)");
            System.out.println("8 - Витягти бронювання з TXT-файлу");
            System.out.println("9 - Вийти");
            System.out.println("---------------------------------");
            command = scan.nextInt();
            switch(command){
                case 1:
                    System.out.println("Введіть ваші паспортні дані:");
                    scan.nextLine();
                    Hotel vac = new Hotel();
                    vac.setName(scan.nextLine());
                    vacancyList.add(vac);
                    System.out.println("Бронювання '"+vac.getName()+"' додано з ідентифікатором: "+vac.getId());
                    break;
                case 2:
                    System.out.println("Введіть ідентифікатор бронювання, щоб видалити:");
                    id = scan.nextInt();
                    Iterator<Hotel> myItr = vacancyList.iterator();
                    boolean isFound = false;
                    while(myItr.hasNext()){
                        Hotel t = myItr.next();
                        if(t.getId() == id){
                            myItr.remove();
                            isFound = true;
                            break;
                        }
                    }
                    if(!isFound){
                        System.err.println("Такого бронювання не знайдено!");
                    }
                    else{
                        System.out.println("Бронювання видалено!");
                    }
                    break;
                case 3:
                    Iterator<Hotel> myItr1 = vacancyList.iterator();
                    while(myItr1.hasNext()){
                        Hotel t = myItr1.next();
                        System.out.println(t.toString());
                    }
                    break;
                case 4:
                    System.out.println("Введіть ID бронювання , щоб редагувати його: ");
                    id = scan.nextInt();
                    Hotel temp = vacFind(id,vacancyList);
                    if(temp != null){
                        System.out.println("Бронювання знайдено!");
                        boolean exit = false;
                        while(!exit) {
                            if(autoMode){
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                }catch(InterruptedException e){

                                }
                            }
                            System.out.println(temp.toString());
                            System.out.println("-----------------------------");
                            System.out.println("1 - Змінити паспортні дані");
                            System.out.println("2 - Змінити дату поселення");
                            System.out.println("3 - Змінити дату виселення");
                            System.out.println("4 - Змінити номер кімнати");
                            System.out.println("5 - Змінити причину поселення");
                            System.out.println("6 - Повернутись до гол. меню");
                            System.out.println("-----------------------------");
                            command  = scan.nextInt();
                            switch(command){
                                case 1:
                                    System.out.println("Введіть нові паспортні дані:");
                                    scan.nextLine();
                                    temp.setName(scan.nextLine());
                                    System.out.println("Успішно змінено!");
                                    break;
                                case 2:
                                    System.out.println("Введіть нову дату поселення:");
                                    scan.nextLine();
                                    temp.setDate1(scan.nextLine());
                                    System.out.println("Успішно змінено!");
                                    break;
                                case 3:
                                    System.out.println("Введіть нову дату виселення:");
                                    scan.nextLine();
                                    temp.setDate2(scan.nextLine());
                                    System.out.println("Успішно змінено!");
                                    break;
                                case 4:
                                    System.out.println("Введіть номер ");
                                    scan.nextLine();
                                    temp.setRoom(scan.nextLine());
                                    System.out.println("Успішно змінено!");
                                    break;
                                case 5:
                                    System.out.println("Введіть причину поселення:");
                                    scan.nextLine();
                                    temp.setReason(scan.nextLine());
                                    System.out.println("Успішно додано!");
                                    break;
                                case 6:
                                    exit = true;
                                    break;
                            }
                        }
                    }
                    else{
                        System.out.println("Бронювання не знайдено!");
                    }
                    break;
                case 5: {
                    FileOutputStream fos;
                    System.out.println("----Введіть назву файлу:");
                    scan.nextLine();
                    String file_name = scan.nextLine();
                    System.out.println("----Виберіть потрібну директорію, щоб зберегти файл:");
                    String path = FileManager.selectDir(scan) + "\\" + file_name;
                    if (!(new File(path)).exists()) {
                        File newFile = new File(path);
                        try {
                            if (newFile.createNewFile())
                                System.out.println("***Файл '" + file_name + "' було створено!");
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                            break;
                        }
                    }
                    try {
                        fos = new FileOutputStream(path);
                    } catch (FileNotFoundException ex) {
                        System.err.println("Файл не знайдено!");
                        break;
                    }
                    XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(fos));
                    encoder.writeObject(vacancyList.size());
                    for (Hotel one : vacancyList) {
                        encoder.writeObject(one);
                    }
                    encoder.close();
                    System.out.println("Успішно записано!");
                }
                break;
                case 6: {
                    System.out.println("1 - Створити новий список(не зберігається попередній)");
                    System.out.println("2 - Додати до поточного списку");
                    command = scan.nextInt();
                    switch (command) {
                        case 1:
                            System.out.println("----Виберіть файл:");
                            scan.nextLine();
                            String path_ = FileManager.selectFile(scan);
                            FileInputStream fis;
                            try {
                                fis = new FileInputStream(path_);
                            } catch (FileNotFoundException ex) {
                                System.err.println("FileNotFound");
                                break;
                            }
                            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(fis));
                            Integer size = (Integer) decoder.readObject();
                            vacancyList = new ArrayList<>();
                            Hotel.cleanVacancy();
                            for (int i = 0; i < size; i++) {
                                vacancyList.add((Hotel) decoder.readObject());
                            }
                            decoder.close();

                            break;
                        case 2:
                            System.out.println("----Виберіть файл:");
                            scan.nextLine();
                            String path__ = FileManager.selectFile(scan);
                            FileInputStream fis_;
                            try {
                                fis_ = new FileInputStream(path__);
                            } catch (FileNotFoundException ex) {
                                System.err.println("FileNotFound");
                                break;
                            }
                            XMLDecoder decoder_ = new XMLDecoder(new BufferedInputStream(fis_));
                            Integer size_ = (Integer) decoder_.readObject();
                            for (int i = 0; i < size_; i++) {
                                vacancyList.add((Hotel) decoder_.readObject());
                            }
                            decoder_.close();
                            break;
                    }
                }
                break;
                case 7: {
                    FileOutputStream fos;
                    System.out.println("----Введіть назву файлу(TXT):");
                    scan.nextLine();
                    String file_name = scan.nextLine();
                    System.out.println("----Виберіть потрібну директорію, щоб зберегти файл:");
                    String file = FileManager.selectDir(scan) + "\\" + file_name;
                    if (!(new File(file)).exists()) {
                        File newFile = new File(file);
                        try {
                            if (newFile.createNewFile())
                                System.out.println("New file '" + file_name + "' has been created");
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                            break;
                        }
                    }
                    try {
                        fos = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        break;
                    }
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(vacancyList.size());
                        for (Hotel one : vacancyList) {
                            oos.writeObject(one);
                        }
                        oos.flush();
                        oos.close();
                        fos.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                        break;
                    }
                }
                break;
                case 8:
                {
                    System.out.println("----Виберіть файл:");
                    scan.nextLine();
                    String path = FileManager.selectFile(scan);
                    FileInputStream fis;
                    try {
                        fis = new FileInputStream(path);
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found!");
                        break;
                    }
                    try {
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        Integer count = (Integer) ois.readObject();
                        for (int i = 0; i < count; i++) {
                            vacancyList.add((Hotel) ois.readObject());
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage() + "dsds");
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println("Not Found!");
                        break;
                    }
                }break;
                case 9:
                    System.exit(0);
                    break;

            }
        }
    }

}