// XmlOptimizer.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include <iostream>
#include <cstdlib>
#include <tchar.h>
#include <Windows.h>
#include "tinyxml2.h"

int main()
{
    char pszFolder[256] = { 0 };
    char pszPath[256] = { 0 };
    std::cout << "[*] Please input the Folder string: " << std::endl;
    std::cin.getline(pszFolder, 256);
    std::cout << "[*] All Folder element in Xml files will be changed to: " << pszFolder << std::endl;
    std::cout << "[*] Please input the Path string (without the last \'/\' and file name itself): " << std::endl;
    std::cin.getline(pszPath, 256);
    std::cout << "[*] All Path element in Xml files will be changed to: " << pszPath << "/FileName." << std::endl;
    std::system("pause");
    WIN32_FIND_DATA fdFindFileData;
    HANDLE hFind = INVALID_HANDLE_VALUE;
    hFind = FindFirstFile(TEXT("*.xml"), &fdFindFileData);
    if (hFind == INVALID_HANDLE_VALUE) {
        std::cout << "[!] Error in FindFirstFile." << std::endl;
        if (GetLastError() == ERROR_FILE_NOT_FOUND) std::cout << "[!] No xml file found." << std::endl;
        else std::cout << "[!] Unkown error." << std::endl;
        return 0;
    }
    tinyxml2::XMLDocument xmlInputFile;
    char pszOrgXmlFileName[256];
    TCHAR ptszOrgImgFileName[256];
    TCHAR ptszOutputImgFileName[256];
    char pszOutputImgFileName[256];
    char pszOutputXmlFileName[256];
    char pszOutputPath[256];
    int nFileNo = 1;
    do {
        WideCharToMultiByte(CP_ACP, 0, fdFindFileData.cFileName, _countof(fdFindFileData.cFileName), pszOrgXmlFileName, 256, NULL, NULL);
        std::cout << "[-] Found file name: " << pszOrgXmlFileName << std::endl;
        if (xmlInputFile.LoadFile(pszOrgXmlFileName) != 0) {
            std::cout << "[!] Error in XML::LoadFile. This Xml file is skipped." << std::endl;
            xmlInputFile.Clear();
            continue;
        }
        _tcscpy_s(ptszOrgImgFileName, 256, fdFindFileData.cFileName);
        int nFileNameLength = _tcsclen(ptszOrgImgFileName);
        ptszOrgImgFileName[nFileNameLength - 4] = '\0';
        _tcscat_s(ptszOrgImgFileName, 256, TEXT(".jpg"));    // Or PNG file.
        _stprintf_s(ptszOutputImgFileName, 256, TEXT("%04d.jpg"), nFileNo);  // Or PNG file.
        WideCharToMultiByte(CP_ACP, 0, ptszOutputImgFileName, _countof(ptszOutputImgFileName), pszOutputImgFileName, 256, NULL, NULL);
        sprintf_s(pszOutputXmlFileName, 256, "%04d.xml", nFileNo);
        sprintf_s(pszOutputPath, 256, "%s/%s", pszPath, pszOutputImgFileName);
        tinyxml2::XMLElement* xeRoot = xmlInputFile.RootElement();
        tinyxml2::XMLElement* xeFolder = xeRoot->FirstChildElement("folder");
        tinyxml2::XMLElement* xeFileName = xeRoot->FirstChildElement("filename");
        tinyxml2::XMLElement* xePath = xeRoot->FirstChildElement("path");
        xeFolder->SetText(pszFolder);
        xeFileName->SetText(pszOutputImgFileName);
        xePath->SetText(pszOutputPath);
        tinyxml2::XMLElement* xeObject = xeRoot->FirstChildElement("object");
        while (xeObject != NULL) {
            tinyxml2::XMLElement* xeName = xeObject->FirstChildElement("name");
            xeName->SetText("Bone");
            xeObject = xeObject->NextSiblingElement("object");
        }
        std::cout << "[-] Name of objects are all converted to Bone." << std::endl;
        if (MoveFile(ptszOrgImgFileName, ptszOutputImgFileName) == 0)
            std::cout << "[!] Error in MoveFile. Could not rename the image file. The corresponding image file is skipped." << std::endl;
        else std::cout << "[-] Renamed the corresponding image file." << std::endl;
        if (xmlInputFile.SaveFile(pszOutputXmlFileName) == 0) std::cout << "[-] Xml file saved." << std::endl;
        else {
            std::cout << "[!!] Fatal Error in XML::SaveFile." << std::endl;
            break;
        }
        nFileNo++;
        xmlInputFile.Clear();
        if (DeleteFile(fdFindFileData.cFileName) != 0) std::cout << "[-] Deleted the original Xml file." << std::endl;
        else std::cout << "[!] Error in DeleteFile. Could not delete the original Xml file and it is skipped." << std::endl;
    } while (FindNextFile(hFind, &fdFindFileData) != 0);
    FindClose(hFind);
    hFind = INVALID_HANDLE_VALUE;
    std::cout << "[-] Conversion stopped." << std::endl;
    std::system("pause");
    return 0;
}
