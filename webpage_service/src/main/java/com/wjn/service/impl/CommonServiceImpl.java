package com.wjn.service.impl;

import com.wjn.blog.familyblog.bean.index.dto.BannerDto;
import com.wjn.blog.familyblog.bean.index.dto.HeaderDto;
import com.wjn.blog.familyblog.bean.index.dto.MenuDto;
import com.wjn.blog.familyblog.mapper.BannerMapper;
import com.wjn.blog.familyblog.mapper.HeaderMapper;
import com.wjn.blog.familyblog.mapper.MenuMapper;
import com.wjn.blog.familyblog.model.Banner;
import com.wjn.blog.familyblog.model.BeanConstant;
import com.wjn.blog.familyblog.model.Header;
import com.wjn.blog.familyblog.service.view.CommonService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.wjn.blog.familyblog.common.constant.GlobalConstant.TRUE;

/**
 * @auther WJN
 * @date 2019/9/30 16:49
 * @describetion
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Resource
    private BannerMapper bannerMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private HeaderMapper headerMapper;

    /**
     * 通过查询数据库获取目录名称和目录的网址，然后封装给MenuDto，再添加进集合返回
     * @return list
     */
    @Override
    public List<MenuDto> getMenu() {
        List<Map<String,Object>> list =  menuMapper.getMenu();
        List<MenuDto> menuDtos = new LinkedList<>();
        for (Map<String, Object> map : list) {
            MenuDto menuDto = MenuDto.of()
                    .setMenuName(map.get(BeanConstant.MENUS).toString())
                    .setUrl(map.get(BeanConstant.URL).toString());
            menuDtos.add(menuDto);
        }
        return menuDtos;
    }

    /**
     * 通过通用Mapper查询，查询条件未state为1的主题，因为启用的主题只能有一个，故直接使用headers.get(0)
     * 再把Header赋值给HeaderDto，返回。
     * @return HeaderDto
     */
    @Override
    public HeaderDto getHeader() {
        Example example = new Example(Header.class);
        example.createCriteria().andEqualTo(BeanConstant.STATE,TRUE);
        List<Header> headers = headerMapper.selectByExample(example);
        HeaderDto headerDto = new HeaderDto();
        Header.conver(headers.get(0), headerDto);
        return headerDto;
    }

    @Override
    public BannerDto getBanner(String html) {

        Example example = new Example(Banner.class);
        example.createCriteria().andEqualTo("html",html);
        List<Banner> banners = bannerMapper.selectByExample(example);
        BannerDto bannerDto = BannerDto.of();
        Banner.conver(banners.get(0),bannerDto);
        return bannerDto;
    }

}
