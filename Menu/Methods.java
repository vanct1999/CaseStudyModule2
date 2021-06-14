package Menu;

import Manage.*;

import java.util.ArrayList;
import java.util.Scanner;

import Method.*;
public class Methods {
    MethodsBill methodsBill = new MethodsBill();
    MethodsComputer methodsComputer = new MethodsComputer();
    MethodsPlayer methodsPlayer = new MethodsPlayer();
    MethodsAccount methodsAccount = new MethodsAccount();
    int id = 1;
    Scanner scanner = new Scanner(System.in);
    public Computer getInfoComputer(){
        boolean checkId = false;
        ArrayList<Services> services = new ArrayList<>();
        Computer computer = new Computer();
        while (!checkId){
            System.out.println("Nhập số của máy tính muốn thêm :");
            id = Integer.parseInt(scanner.nextLine());
            checkId = methodsComputer.checkId(id);
            if (!checkId){
                System.err.println("Id đã tồn tại.");
            }
        }
        computer.setId(id);
        System.out.println("Nhập vào tên của người chơi máy này ");
        String name = scanner.nextLine();
        Player a = methodsPlayer.getPlayerByName(name);
        computer.setPlayer(a);
        System.out.println("Thời gian bắt đầu chơi : ");
        int timeStart = Integer.parseInt(scanner.nextLine());
        computer.setStartTime(timeStart);
        System.out.println("Thời gian trả máy : ");
        int timeEnd = Integer.parseInt(scanner.nextLine());
        computer.setEndTime(timeEnd);
        System.out.println(" Dịch vụ: ");
        int count = Integer.parseInt(scanner.nextLine());
        System.out.println("----Bảng giá dịch vụ thêm----");
        System.out.println("1: Đồ uống 150000 ");
        System.out.println("2: Đồ ăn 25000 ");
        for (int i = 0; i < count; i++) {
            System.out.println("Gồm những dịch vụ nào ? ");
            int idServices = Integer.parseInt(scanner.nextLine());
            Services c = MethodsServices.getServiceById(idServices);
            services.add(c);
        }
        computer.setServices(services);
        return computer;
    }
    public Player getInfoPlayer(){
        Player p = new Player();
        System.out.println("Nhập tên người chơi ");
        String name = scanner.nextLine();
        p.setName(name);
        System.out.println("Nhập tuổi ");
        int age = Integer.parseInt(scanner.nextLine());
        p.setAge(age);
        System.out.println("Số chứng minh nhân dân ?");
        int identity = Integer.parseInt(scanner.nextLine());
        p.setIdentityCard(identity);
        return p;
    }
    public Bill getBill(){
        boolean checkId = false;
        Bill bill = new Bill();
        while (!checkId){
            System.out.println("Nhập mã hóa đơn :");
            id = Integer.parseInt(scanner.nextLine());
            checkId = methodsBill.checkId(id);
            if (!checkId){
                System.err.println("Mã này đã tồn tại!");
            }
        }
        bill.setId(id);
        System.out.println("Nhập tên người cần thanh toán : ");
        String name = scanner.nextLine();
        Player player = methodsPlayer.getPlayerByName(name);
        bill.setPlayer(player);
        System.out.println("Nhập số máy người chơi : ");
        int idComputer = Integer.parseInt(scanner.nextLine());
        Computer computer = methodsComputer.getComputerById(idComputer);
        int start = computer.getStartTime();
        bill.setStartTime(start);
        int end = computer.getEndTime();
        bill.setEndTime(end);
        bill.setTime(end,start);
        double result = 0;
        for (Services i : computer.getServices()) {
            result += i.getPrices();
        }
        bill.setPricesServices(result);
        bill.setCaculate(bill.getTime(),result);
        return bill;
    }
    public Account getAccount(){
        String idAcccount = "";
        Account account = new Account();
        boolean checkId = false;
        while (!checkId){
            System.out.println("Id: ");
            idAcccount = scanner.nextLine();
            checkId = methodsAccount.checkId(idAcccount);
            if (!checkId){
                System.err.println("Id đã tồn tại!");
            }
        }
        account.setId(idAcccount);
        System.out.println("Password: ");
        String passWord = scanner.nextLine();
        if (MethodsAccount.checkPass(passWord)){
            account.setPassWord(passWord);
        }else {
            System.out.println("Vui lòng nhập đúng yêu cầu. ");
            getAccount();
        }

        return account;
    }
    public boolean checkAccount(){
        boolean a = false;
        System.out.println("Đăng Nhập");
        System.out.println("Id: ");
        String id = scanner.nextLine();
        System.out.println("Password: ");
        String passWord = scanner.nextLine();
        a = methodsAccount.checkId(id,passWord);
        return a;
    }
}
